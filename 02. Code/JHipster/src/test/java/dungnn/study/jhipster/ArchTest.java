package dungnn.study.jhipster;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("dungnn.study.jhipster");

        noClasses()
            .that()
                .resideInAnyPackage("dungnn.study.jhipster.service..")
            .or()
                .resideInAnyPackage("dungnn.study.jhipster.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..dungnn.study.jhipster.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
