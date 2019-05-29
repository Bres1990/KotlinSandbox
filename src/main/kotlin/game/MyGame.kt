package game

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.FPSLogger
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.kotcrab.vis.ui.VisUI
import com.kotcrab.vis.ui.widget.Menu
import com.kotcrab.vis.ui.widget.MenuBar
import com.kotcrab.vis.ui.widget.MenuItem
import game.screen.CharacterCreation
import game.screen.MainMenu
import game.screen.MyGameScreen


class MyGame: Game(), MenuBar.MenuBarListener {

    private lateinit var fpsLogger: FPSLogger

    private lateinit var stage: Stage
    private lateinit var root: Table
    private lateinit var menuBar: MenuBar

    override fun getScreen(): MyGameScreen {
        return super.getScreen() as MyGameScreen
    }

    override fun create() {
        VisUI.load(VisUI.SkinScale.X1)
        setScreen(MainMenu())
        fpsLogger = FPSLogger()
        fpsLogger.log()


        stage = Stage(ScreenViewport())
        root = Table()
        root.setFillParent(true)

        menuBar = MenuBar()
        menuBar.setMenuListener(this)
    }

    override fun render() {
        val currentScreen: MyGameScreen = getScreen()
        currentScreen.render(Gdx.graphics.deltaTime)

        if (currentScreen.isDone()) {
            currentScreen.dispose()

            if (currentScreen is MainMenu) {
                setScreen(CharacterCreation())
            } else if (currentScreen is CharacterCreation) {
                setScreen(MainMenu())
            }
        } else if (currentScreen.isEscaped()) {
            currentScreen.dispose()
            Gdx.app.exit()
        }

        if (currentScreen is CharacterCreation) {
            if (!stage.actors.contains(root)) {
                stage.addActor(root)

                Gdx.input.inputProcessor = stage

                root.add(menuBar.table).fillX().expandX().row()
                root.add().expand().fill()

                createMenus()
            }

            stage.act(Math.min(Gdx.graphics.deltaTime, 1 / 30f));
            stage.draw()
        }
    }

    override fun menuOpened(menu: Menu?) {
        menu!!.showMenu(stage, root)
        System.out.println("Menu opened: ${menu.title}")
    }

    override fun menuClosed(menu: Menu?) {
        System.out.println("Menu closed: ${menu!!.title}")
        if (stage.actors.contains(menu)) {
            menu.remove()
        }
    }

    private fun createMenus() {
        val fileMenu = Menu("File")
        val editMenu = Menu("Edit")
        val windowMenu = Menu("Window")

        fileMenu.addItem(MenuItem("F1").setShortcut("f1"))
        fileMenu.addItem(MenuItem("F2").setShortcut("f2"))
        fileMenu.addItem(MenuItem("Quit").setShortcut("alt + f4"))

        fileMenu.setPosition(0f, menuBar.table.y, 0)

        editMenu.addItem(MenuItem("edit #1"))
        editMenu.addItem(MenuItem("edit #2"))
        editMenu.addItem(MenuItem("edit #3"))
        editMenu.addItem(MenuItem("edit #4"))

        val disabledMenuItem = MenuItem("disabled")
        disabledMenuItem.isDisabled = true

        editMenu.addItem(disabledMenuItem)

        windowMenu.addItem(MenuItem("window #1"))
        windowMenu.addItem(MenuItem("window #2"))
        windowMenu.addItem(MenuItem("window #3"))
        windowMenu.addItem(MenuItem("window #4"))

        menuBar.addMenu(fileMenu)
        menuBar.addMenu(editMenu)
        menuBar.addMenu(windowMenu)
    }

    override fun dispose() {
        VisUI.dispose()
        stage.dispose()
    }
}