package example.micronaut.datastore

import com.google.cloud.datastore.Datastore
import com.google.cloud.datastore.DatastoreOptions
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Primary
import io.micronaut.context.annotation.Requires
import javax.inject.Singleton

@Factory
@Requires(notEnv = ["test"])
class DatastoreFactory {

    @Singleton
    fun createPrimaryDatastore(): Datastore  {
        // Real settings would go here
        return DatastoreOptions
                .newBuilder()
                .build()
                .service
    }

}