package com.evgeny.goncharov.settings.ui

import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.text.set
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.evgeny.goncharov.coreapi.WithFacade
import com.evgeny.goncharov.coreapi.base.BaseFragment
import com.evgeny.goncharov.coreapi.utils.Language
import com.evgeny.goncharov.coreapi.utils.SingleLiveEvent
import com.evgeny.goncharov.settings.R
import com.evgeny.goncharov.settings.di.components.SettingsComponent
import com.evgeny.goncharov.settings.events.SettingUiEvents
import com.evgeny.goncharov.settings.models.ThemeModel
import com.evgeny.goncharov.settings.view.model.SettingsViewModel
import kotlinx.android.synthetic.main.fragment_settings.*
import javax.inject.Inject

class SettingsFragment : BaseFragment() {

    companion object {
        fun getInstance() = SettingsFragment()
    }

    @Inject
    lateinit var viewModel: SettingsViewModel

    private var lang: Language = Language.RU

    private lateinit var themeLiveData: LiveData<ThemeModel>
    private lateinit var languageLiveData: LiveData<Language>
    private lateinit var uiEventLiveData: LiveData<SettingUiEvents>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDaggerGraph()
        savedInstanceState ?: viewModel.initInjection()
        init()
    }

    private fun initDaggerGraph() {
        SettingsComponent.init(
            this,
            (requireActivity() as WithFacade).getFacade()
        ).inject(this)
    }

    override fun getLayoutId(): Int = R.layout.fragment_settings

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUi()
    }

    private fun initUi() {
        initToolbar()
        initClickThemeApp()
        initClickLanguageChoose()
    }

    private fun init() {
        initLiveData()
        viewModel.initThemeToView()
        viewModel.initLanguageToView()
    }

    private fun initLiveData() {
        themeLiveData = viewModel.getThemeLiveData()
        languageLiveData = viewModel.getLanguageLiveData()
        uiEventLiveData = viewModel.getUiEvents()
        themeLiveData.observe(this, Observer { model ->
            model?.let {
                setThemeModel(model)
            }
        })
        languageLiveData.observe(this, Observer { lang ->
            lang?.let {
                this.lang = lang
                setLanguageApp(lang)
            }
        })
        uiEventLiveData.observe(this, Observer { event ->
            when (event) {
                SettingUiEvents.ChooseLanguageApp,
                SettingUiEvents.ChooseThemeApp
                -> activity?.recreate()
            }
        })
    }

    private fun setThemeModel(value: ThemeModel) {
        when (value.themeValue) {
            R.style.AppThemeNight -> initNightTheme()
            R.style.AppThemeDay -> initLightTheme()
        }
    }

    private fun initNightTheme() {
        initSpannableTextView(
            title = R.string.theme_title_settings,
            subTitle = R.string.settings_night_title,
            drawStart = R.drawable.ic_theme_night,
            textView = txvThemeApp
        )
    }

    private fun initLightTheme() {
        initSpannableTextView(
            title = R.string.theme_title_settings,
            subTitle = R.string.settings_light_title,
            drawStart = R.drawable.ic_theme,
            textView = txvThemeApp
        )
    }

    private fun initSpannableTextView(
        @StringRes title: Int,
        @StringRes subTitle: Int,
        @ColorRes colorTitle: Int = getColorTitle(),
        @ColorRes colorSubTitle: Int = getColorSubtitle(),
        @DrawableRes drawStart: Int,
        textView: AppCompatTextView
    ) {
        val titleStr = requireActivity().getString(title)
        val subTitleStr = requireActivity().getString(subTitle)
        val resultTitle = SpannableString("$titleStr\n$subTitleStr")
        resultTitle[0..titleStr.length] = ForegroundColorSpan(
            ContextCompat.getColor(requireContext(), colorTitle)
        )
        resultTitle[titleStr.length..titleStr.length + subTitleStr.length + 1] =
            ForegroundColorSpan(
                ContextCompat.getColor(requireContext(), colorSubTitle)
            )
        textView.text = resultTitle
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(ContextCompat.getDrawable(
            requireContext(),
            drawStart
        ), null, null, null)
    }

    private fun initClickThemeApp() {
        txvThemeApp.setOnClickListener {
            val dialog = DialogChooseThemeApp()
            dialog.show(requireFragmentManager(), DialogChooseThemeApp::class.java.name)
        }
    }

    private fun initToolbar() {
        toolbar.apply {
            when (themeManager.getThemeNow()) {
                R.style.AppThemeDay -> setNavigationIcon(R.drawable.ic_arrow_back_black)
                R.style.AppThemeNight -> setNavigationIcon(R.drawable.ic_arrow_back_black_night)
            }
            setNavigationOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }
            setTitle(R.string.settings_to_cat_title)
        }
    }

    private fun setLanguageApp(language: Language) {
        when (language) {
            Language.RU -> initRuLanguageTitle()
            Language.EN -> initEnLanguageTitle()
        }
    }

    private fun initRuLanguageTitle() {
        initSpannableTextView(
            title = R.string.language_app_title,
            subTitle = R.string.language_app_title_ru,
            drawStart = getIconTheme(),
            textView = txvLanguageApp
        )
    }

    private fun getIconTheme() = when (themeManager.getThemeNow()) {
        R.style.AppThemeNight -> R.drawable.ic_global_night
        else -> R.drawable.ic_global
    }

    private fun initEnLanguageTitle() {
        initSpannableTextView(
            title = R.string.language_app_title,
            subTitle = R.string.language_app_title_en,
            drawStart = getIconTheme(),
            textView = txvLanguageApp
        )
    }

    private fun getColorTitle(): Int = when (viewModel.getThemeNow()) {
        R.style.AppThemeNight -> R.color.white
        else -> R.color.text_toolbar_title_light
    }

    private fun getColorSubtitle(): Int = when (viewModel.getThemeNow()) {
        R.style.AppThemeNight -> R.color.white_hint
        else -> R.color.color_dark_grey
    }

    private fun initClickLanguageChoose() {
        txvLanguageApp.setOnClickListener {
            val dialog = DialogChooseLanguageApp()
            dialog.show(requireFragmentManager(), DialogChooseLanguageApp::class.java.name)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        SettingsComponent.component = null
        (themeLiveData as SingleLiveEvent).call()
        (languageLiveData as SingleLiveEvent).call()
        (uiEventLiveData as SingleLiveEvent).call()
    }
}