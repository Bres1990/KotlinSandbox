package game

import com.badlogic.ashley.core.PooledEngine
import game.ashley.*
import java.util.logging.Logger

class AshleyTest {
//    val world = createWorld()
//    val body = world.body {
//        box(width=2f, height=1f) {
//            density=40f
//        }
//    }
//
//    val fixture = body.box(width=2f, height=1f) {
//        density=40f
//    }
}

private val logger = Logger.getLogger("gameworld")

fun main() {
    val engine = PooledEngine()

    val movementSystem = MovementSystem(0)
    val positionSystem = PositionSystem(0)

    engine.addSystem(movementSystem)
    engine.addSystem(positionSystem)

    val listener = Listener()
    engine.addEntityListener(listener)

    for (i in 0 until 9) {
        val entity = engine.createEntity()
        entity.add(PositionComponent(10f, 0f))
        if (i > 5) entity.add(MovementComponent(10f, 2f))

        engine.addEntity(entity)
    }

    logger.info("MovementSystem has ${movementSystem.entities.size()} entities")
    logger.info("PositionSystem has ${positionSystem.entities.size()} entities")

    for (i in 0 until 9) {
        engine.update(0.25f)

        if (i > 5) engine.removeSystem(movementSystem)
    }

    engine.removeEntityListener(listener)
}