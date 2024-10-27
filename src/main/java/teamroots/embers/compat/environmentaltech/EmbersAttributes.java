package teamroots.embers.compat.environmentaltech;

import com.valkyrieofnight.um.api.attribute.AttributeID;
import com.valkyrieofnight.um.api.attribute.IAttribute;
import com.valkyrieofnight.um.api.base.attributes.generic.AttributeBoolean;

public class EmbersAttributes {
    public static final AttributeID EM_EMBER_ID = new AttributeID("embers", "ember", AttributeBoolean.CALCULATOR_OR);
    public static IAttribute EM_EMBER;

    public EmbersAttributes() {
    }

    static {
        EM_EMBER = new AttributeBoolean(EM_EMBER_ID, true);
    }
}
