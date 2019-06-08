package game.screen

import com.badlogic.gdx.Screen

abstract class MyGameScreen : Screen {

    abstract fun update(delta: Float)
    abstract fun draw(delta: Float)
    abstract fun isDone() : Boolean
    abstract fun isEscaped() : Boolean

    override fun hide() {

    }

    override fun show() {

    }

    override fun render(delta: Float) {
        update(delta)
        draw(delta)
    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun resize(width: Int, height: Int) {

    }

    override fun dispose() {

    }
}