package game.ashley

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.EntityListener
import java.util.logging.Logger

class Listener : EntityListener {

    private val logger = Logger.getLogger("listener")

    override fun entityRemoved(entity: Entity?) {
        logger.info("Entity removed: $entity")
    }

    override fun entityAdded(entity: Entity?) {
        logger.info("Entity added: $entity")
    }
}