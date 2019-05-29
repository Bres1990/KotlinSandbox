package game.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Matrix4

class CharacterCreation : MyGameScreen() {

    private var isDone: Boolean = false
    private var isEscaped: Boolean = false

    private var viewMatrix = Matrix4()
    private var transformMatrix = Matrix4()
    private var spriteBatch: SpriteBatch = SpriteBatch()

    private var background: Texture = Texture(Gdx.files.local("background.png"))

    override fun update(delta: Float) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            isDone = true
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            isEscaped = true
        }
    }

    override fun draw(delta: Float) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        viewMatrix.setToOrtho2D(0f, 0f, 1600f, 900f)

        spriteBatch.projectionMatrix = viewMatrix
        spriteBatch.transformMatrix = transformMatrix
        spriteBatch.begin()
        spriteBatch.disableBlending()
        spriteBatch.draw(background, 0f, 0f, 1600f, 900f, 0, 0, 1600, 900, false, false)
        spriteBatch.end()
    }

    override fun isDone(): Boolean {
        return isDone
    }

    override fun isEscaped(): Boolean {
        return isEscaped
    }



}