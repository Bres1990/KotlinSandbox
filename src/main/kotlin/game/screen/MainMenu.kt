package game.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.loaders.BitmapFontLoader
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Matrix4

class MainMenu : MyGameScreen() {

    private var isDone: Boolean = false

    private var viewMatrix = Matrix4()
    private var transformMatrix = Matrix4()
    private var glyphLayout = GlyphLayout()

    private var spriteBatch: SpriteBatch = SpriteBatch()
    private var background: Texture = Texture(Gdx.files.local("background.png"))
    private var logo: Texture = Texture(Gdx.files.local("tank_logo.jpg"))
    private var font: BitmapFont = BitmapFont()

    override fun update(delta: Float) {
        if (Gdx.input.justTouched()) {
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
        spriteBatch.draw(background, 0f, 0f, 1600f, 900f, 0, 0, 1600, 900, false, false)
        spriteBatch.enableBlending()
        spriteBatch.draw(logo, 0f, 0f, 1600f, 900f, 0, 0, 683, 387, false, false)
        spriteBatch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA)

        glyphLayout.setText(font, "Touch screen to start!")
        font.draw(spriteBatch, glyphLayout, 800f, 450f)
        spriteBatch.end()
    }

    override fun isDone(): Boolean {
        return isDone
    }

    override fun dispose() {
        spriteBatch.dispose()
        background.dispose()
        logo.dispose()
        font.dispose()
    }
}