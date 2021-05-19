package example.micronaut

import com.google.cloud.datastore.Datastore
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Requires
import io.mockk.mockk
import javax.inject.Singleton

/*
See README for a workaround which uses this factory
 */

@Factory
@Requires(env = ["xxx"])  // Take this factory out of play until we wish to test with it
//@Requires(env = ["test"])   // Use this factory in tests
class MockDatastoreFactory {

    @Singleton
    fun createDatastoreForCaching(): Datastore {
        return mockk(relaxed = true)
    }

}