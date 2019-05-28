package game.ashley

import com.badlogic.ashley.core.*
import com.badlogic.ashley.utils.ImmutableArray
import java.util.logging.Logger

class MovementSystem(priority: Int) : EntitySystem(priority) {

    lateinit var entities: ImmutableArray<Entity>

    private val logger = Logger.getLogger("movement")

    private val mm: ComponentMapper<MovementComponent> = ComponentMapper.getFor(MovementComponent::class.java)
    private val pm: ComponentMapper<PositionComponent> = ComponentMapper.getFor(PositionComponent::class.java)

    override fun removedFromEngine(engine: Engine?) {
        logger.info("MovementSystem removed from Engine.")
        engine!!.removeAllEntities()
        entities = engine.getEntitiesFor(Family.all(PositionComponent::class.java, MovementComponent::class.java).get())
    }

    override fun update(deltaTime: Float) {
        for (i in 0 until entities.size()-1) {
            val e = entities.get(i)
            val p = pm.get(e)
            val m = mm.get(e)

            p.x += m.velocityX * deltaTime
            p.y += m.velocityY * deltaTime
        }

        logger.info("${entities.size()} entities updated in MovementSystem" )
    }

    override fun addedToEngine(engine: Engine?) {
        entities = engine!!.getEntitiesFor(Family.all(PositionComponent::class.java, MovementComponent::class.java).get())
        logger.info("MovementSystem added to Engine.")
    }
}