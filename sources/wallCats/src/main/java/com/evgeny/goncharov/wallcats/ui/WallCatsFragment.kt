package com.evgeny.goncharov.wallcats.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.evgeny.goncharov.coreapi.activity.contracts.WithFacade
import com.evgeny.goncharov.coreapi.activity.contracts.WithProviders
import com.evgeny.goncharov.coreapi.base.BaseFragment
import com.evgeny.goncharov.coreapi.base.BaseUiEvent
import com.evgeny.goncharov.coreapi.mediators.SearchCatsMediator
import com.evgeny.goncharov.coreapi.mediators.SettingsMediator
import com.evgeny.goncharov.coreapi.mediators.WallCatsMediator
import com.evgeny.goncharov.coreapi.utils.MainThreadExecutor
import com.evgeny.goncharov.coreapi.utils.ViewModelProviderFactory
import com.evgeny.goncharov.domain.SortTypeViewModel
import com.evgeny.goncharov.wallcats.R
import com.evgeny.goncharov.wallcats.di.components.WallCatsComponent
import com.evgeny.goncharov.wallcats.managers.WorkScheduleManager
import com.evgeny.goncharov.wallcats.model.view.CatBreedView
import com.evgeny.goncharov.wallcats.ui.adapters.CatBreedsPagedAdapter
import com.evgeny.goncharov.wallcats.ui.adapters.PageKeyedDataSourceCatBreeds
import com.evgeny.goncharov.wallcats.ui.holders.CatBreedViewHolder
import com.evgeny.goncharov.wallcats.view.model.WallCatsViewModel
import kotlinx.android.synthetic.main.fragment_wall_cats.*
import java.util.concurrent.Executors

/**
 * Экран стены котов
 */
class WallCatsFragment : BaseFragment(),
    CatBreedViewHolder.CatBreedViewHolderListener {

    private val component: WallCatsComponent by lazy {
        WallCatsComponent.getByLazy(
            (requireActivity() as WithFacade).getFacade(),
            (requireActivity() as WithProviders).getProviderAndroidComponent()
        )
    }

    /** Вьюмодель стены котов */
    private val viewModel: WallCatsViewModel by lazy {
        ViewModelProvider(
            this, ViewModelProviderFactory {
                WallCatsViewModel(component.provideInteractor())
            }
        ).get(WallCatsViewModel::class.java)
    }

    /** Для перехода на экран описание кота */
    private lateinit var wallCatsMediator: WallCatsMediator

    /** Для перехода на экран поиска котов */
    private lateinit var searchMediator: SearchCatsMediator

    /** Для перехода на экран настроек */
    private lateinit var settingsMediator: SettingsMediator

    /** Для пангинации списка котов */
    private lateinit var dataSource: PageKeyedDataSourceCatBreeds

    /** Для осуществление пангинации */
    private var mainThreadExecutor = MainThreadExecutor()

    /** Для управления холдерами списка стены котов */
    private lateinit var adapter: CatBreedsPagedAdapter

    /** Получаем события о том что нужно обновить стену котов */
    private lateinit var vmSort: SortTypeViewModel

    /** Шедулер для запуска запланированных задач */
    private lateinit var workSchedulerManager: WorkScheduleManager

    override fun getLayoutId() = R.layout.fragment_wall_cats

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initDaggerGraph()
        init()
        workSchedulerManager.cancelWorksCheckOutUser()
    }

    private fun initDaggerGraph() {
        component.apply {
            wallCatsMediator = provideWallCatsMediator()
            searchMediator = provideSearchCatsMediator()
            settingsMediator = provideSettingMediator()
            themeManager = provideThemeManager()
            vmSort = provideSortViewModel()
            workSchedulerManager = provideWorkScheduleManager()
        }
    }

    private fun init() {
        initLiveData()
        initUi()
    }

    private fun initUi() {
        initToolbar()
        initPagedAdapterAndRecycle()
        initSwipeRefreshLayout()
    }

    private fun initLiveData() {
        viewModel.liveDataUiEvents.observe(this, ::changeUiState)
        vmSort.updateChooseSortType.observe(this, ::updateWallCats)
    }

    private fun updateWallCats(isUpdate: Boolean?) {
        if (isUpdate == true) {
            initPagedAdapterAndRecycle()
        }
    }

    override fun clickCatUrlBreed(urlImage: String?) {
        urlImage?.let {
            val uri = Uri.parse(urlImage)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

    override fun clickCatBreed(id: String?) {
        id?.let {
            wallCatsMediator.goToTheScreenCatDescription(id, requireFragmentManager())
        }
    }

    private fun initSwipeRefreshLayout() {
        swrlContainer.setOnRefreshListener {
            initPagedAdapterAndRecycle()
            swrlContainer.isRefreshing = false
        }
    }

    private fun initPagedAdapterAndRecycle() {
        adapter = CatBreedsPagedAdapter(this, themeManager)
        dataSource = PageKeyedDataSourceCatBreeds(viewModel)
        val pagedConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PAGE_WALL_CATS_SIZE)
            .build()
        val pagedList = PagedList.Builder<Int, CatBreedView>(dataSource, pagedConfig)
            .setNotifyExecutor(mainThreadExecutor)
            .setFetchExecutor(Executors.newCachedThreadPool())
            .build()
        adapter.submitList(pagedList)
        rcvCatBreeds.layoutManager = LinearLayoutManager(context)
        rcvCatBreeds.adapter = adapter
    }

    private fun initToolbar() {
        toolbar.setTitle(R.string.wall_cat_toolbar_title)
        when (themeManager.getThemeNow()) {
            R.style.AppThemeDay -> toolbar.inflateMenu(R.menu.menu_wall_cats_day)
            R.style.AppThemeNight -> toolbar.inflateMenu(R.menu.menu_wall_cats_night)
        }
        toolbar.setOnMenuItemClickListener { menu ->
            when (menu.itemId) {
                R.id.menuSearchCat -> {
                    searchMediator.goToTheSearchScreen(requireFragmentManager())
                    true
                }
                R.id.menuSettings -> {
                    settingsMediator.goToTheSettingsScreen(requireFragmentManager())
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun changeUiState(event: BaseUiEvent<*>?) {
        when (event) {
            BaseUiEvent.EventShowProgress -> {
                hideSomethingWrong()
                showProgress()
            }
            BaseUiEvent.EventHideProgress -> {
                hideProgress()
            }
            is BaseUiEvent.Success<*> -> {
                cnlContentWallCats.isGone = false
            }
            BaseUiEvent.EventSomethingWrong -> {
                cnlContentWallCats.isGone = true
                hideProgress()
                showSomethingWrong()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.liveDataUiEvents.call()
        vmSort.updateChooseSortType.call()
        workSchedulerManager.startWorksCheckOutUser()
        WallCatsComponent.component = null
    }

    companion object {

        fun getInstance() = WallCatsFragment()

        /** Загружаемая страница котят */
        private const val PAGE_WALL_CATS_SIZE = 15
    }
}