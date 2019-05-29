package game

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration

fun main() {
    val config = LwjglApplicationConfiguration()
    config.width = 1600
    config.height = 900
    config.title = "Tanks"
    config.resizable = false
    LwjglApplication(MyGame(), config)
}