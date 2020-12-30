package br.com.pokemon.tradecardgame.pojos;

import br.com.pokemon.tradecardgame.entities.Card;
import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.*;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class pojosTest {

    private static List<PojoClass> pojosList = new ArrayList<>();
    private static List<String> pojos;

    @BeforeEach
    void loading() {
        pojosList.add(PojoClassFactory.getPojoClass(Card.class));
        pojosList.add(PojoClassFactory.getPojoClass(Ability.class));
        pojosList.add(PojoClassFactory.getPojoClass(Attack.class));
        pojosList.add(PojoClassFactory.getPojoClass(Weakness.class));
    }

    @Test
    void mustSuccess_Pojos() {
        Validator validator = ValidatorBuilder.create()
                .with(new SetterMustExistRule(),
                        new GetterMustExistRule())
                .with(new GetterTester())
                .with(new NoPublicFieldsExceptStaticFinalRule())
                .with(new NoStaticExceptFinalRule())
                .with(new NoNestedClassRule())
                .with(new NoFieldShadowingRule())
                .build();
        validator.validate(pojosList);

        validator = ValidatorBuilder.create()
                .with(new SetterTester())
                .build();
        validator.validate(pojosList);
    }
}
