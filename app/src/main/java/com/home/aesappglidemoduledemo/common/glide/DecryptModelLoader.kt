package com.home.aesappglidemoduledemo.common.glide

import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoader.LoadData
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import com.bumptech.glide.signature.ObjectKey
import java.io.InputStream

class DecryptModelLoader : ModelLoader<String?, InputStream?> {

    override fun buildLoadData(
        model: String,
        width: Int,
        height: Int,
        options: Options
    ): LoadData<InputStream?>? {
        return LoadData(ObjectKey(model),
            DecryptDataFetcher(model)
        )
    }

    override fun handles(model: String): Boolean {
        return true
    }

    class Factory : ModelLoaderFactory<String?, InputStream?> {

        override fun build(multiFactory: MultiModelLoaderFactory)
                : ModelLoader<String?, InputStream?> {
            return DecryptModelLoader()
        }

        override fun teardown() {}
    }
}