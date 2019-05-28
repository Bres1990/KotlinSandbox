package game.ashley

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.EntitySystem
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.utils.ImmutableArray
import java.util.logging.Logger

class PositionSystem(priority: Int): EntitySystem(priority) {

    private val logger = Logger.getLogger("position")
    lateinit var entities: ImmutableArray<Entity>

    override fun removedFromEngine(engine: Engine?) {
        logger.info("PositionSystem removed from Engine.")
        engine!!.removeAllEntities()
        entities = engine.getEntitiesFor(Family.all(PositionComponent::class.java).get())
    }

    override fun addedToEngine(engine: Engine?) {
        entities = engine!!.getEntitiesFor(Family.all(PositionComponent::class.java).get())
        logger.info("PositionSystem added to Engine.")
    }
}