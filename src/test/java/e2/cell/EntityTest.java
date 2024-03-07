package e2.cell;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntityTest {

    private final static EntityType ENTITY_TYPE = EntityType.MINE;

    private Entity entity;

    @BeforeEach
    void initEntity(){
        int cellX = 5;
        int cellY = 5;
        this.entity = new GameCell(cellX,cellY, ENTITY_TYPE);
    }

    @Test
    void cellEntitySetCorrectly(){
        assertEquals(ENTITY_TYPE, this.entity.getEntityType());
    }

}
