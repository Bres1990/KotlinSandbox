package game.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Matrix4

class CharacterCreation : MyGameScreen() {

    private var isDone: Boolean = false

    private var viewMatrix = Matrix4()
    private var transformMatrix = Matrix4()
    private var spriteBatch: SpriteBatch = SpriteBatch()

    private var chart: Texture = Texture(Gdx.files.local("chart.png"))

    override fun update(delta: Float) {
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            isDone = true
        }
    }

    override fun draw(delta: Float) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        viewMatrix.setToOrtho2D(0f, 0f, 1600f, 900f)

        spriteBatch.projectionMatrix = viewMatrix
        spriteBatch.transformMatrix = transformMatrix
        spriteBatch.begin()
        spriteBatch.disableBlending()
        spriteBatch.color = Color.GRAY
        spriteBatch.enableBlending()
        spriteBatch.draw(chart, 800f - (640f / 2f), 450f - (245f / 2f), 640f, 245f, 0, 0, 683, 387, false, false)
        spriteBatch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA)
        spriteBatch.end()
    }

    override fun isDone(): Boolean {
        return isDone
    }

}