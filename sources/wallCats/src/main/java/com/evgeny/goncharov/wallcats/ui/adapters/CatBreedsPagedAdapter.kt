package com.evgeny.goncharov.wallcats.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.evgeny.goncharov.coreapi.managers.ThemeManager
import com.evgeny.goncharov.wallcats.R
import com.evgeny.goncharov.wallcats.model.view.CatBreedView
import com.evgeny.goncharov.wallcats.ui.holders.CatBreedViewHolder

/**
 * Адаптер стены котов
 * @property listener контракт слушателя нажатий
 * @property themeManager менеджер отвечающий за темы в приложении
 */
class CatBreedsPagedAdapter(
    private val listener: CatBreedViewHolder.CatBreedViewHolderListener,
    private val themeManager: ThemeManager
) : PagedListAdapter<CatBreedView, CatBreedViewHolder>(DiffUtilsCatBreeds()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatBreedViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.holder_cat_breed, parent, false)
        return CatBreedViewHolder(view, listener, themeManager)
    }

    override fun onBindViewHolder(holder: CatBreedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    /**
     * Для рассчета списков
     */
    class DiffUtilsCatBreeds : DiffUtil.ItemCallback<CatBreedView>() {

        override fun areItemsTheSame(oldItem: CatBreedView, newItem: CatBreedView) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CatBreedView, newItem: CatBreedView) =
            oldItem == newItem
    }
}