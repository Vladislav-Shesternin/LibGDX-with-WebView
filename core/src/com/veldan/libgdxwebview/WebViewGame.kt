package com.veldan.libgdxwebview

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils

class WebViewGame : ApplicationAdapter() {

    private val batch by lazy { SpriteBatch() }
    private val img by lazy { Texture("badlogic.jpg") }

    override fun render() {
        ScreenUtils.clear(Color.RED)
        batch.begend { draw(img, 0f, 0f) }
    }

    override fun dispose() {
        disposeAll(batch, img)
    }
}

