package example.micronaut
import com.google.cloud.datastore.Datastore
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
class DatastoreMockBeanTest {

    @Inject
    lateinit var datastore: Datastore

    @MockBean(Datastore::class)
    fun mockDatastore(): Datastore {
        return mockk(relaxed = true)
    }

    @Test
    fun `datastore is injected`(){
        Assertions.assertNotNull(datastore)
    }

}
