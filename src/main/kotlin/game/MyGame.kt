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
        val newCharacterMenu = Menu("New character")
        val importCharacterMenu = Menu("Import character")
        val optionsMenu = Menu("Options")

        newCharacterMenu.addItem(MenuItem("Set race").setShortcut("f1"))
        newCharacterMenu.addItem(MenuItem("Set class").setShortcut("f2"))
        newCharacterMenu.addItem(MenuItem("Set perks").setShortcut("f3"))
        newCharacterMenu.addItem(MenuItem("Set name").setShortcut("f4"))

        val importCharacterMenuItems = ArrayList<MenuItem>()
        importCharacterMenuItems.add(MenuItem("-- Import slot 1 --"))
        importCharacterMenuItems.add(MenuItem("-- Import slot 2 --"))
        importCharacterMenuItems.add(MenuItem("-- Import slot 3 --"))
        importCharacterMenuItems.add(MenuItem("-- Import slot 4 --"))

        for (menuItem in importCharacterMenuItems) {
            menuItem.isDisabled = true
            importCharacterMenu.addItem(menuItem)
        }

        optionsMenu.addItem(MenuItem("Game"))
        optionsMenu.addItem(MenuItem("Sound"))
        optionsMenu.addItem(MenuItem("Key Bindings"))

        menuBar.addMenu(newCharacterMenu)
        menuBar.addMenu(importCharacterMenu)
        menuBar.addMenu(optionsMenu)
    }

    override fun dispose() {
        VisUI.dispose()
        stage.dispose()
    }
}