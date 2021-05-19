Example project to illustrate https://github.com/micronaut-projects/micronaut-core/issues/5468

### @MicronautTest / @Mockbean issue with Google Datastore

1. Run `DatastoreMockBeanTest` (eg via `./gradlew test` or just via your IDE) - it passes


2. Upgrade micronaut 
    * in `gradle.properties` change 
   `micronautVersion=2.2.1` to `micronautVersion=2.3.0`
    * Note, any version higher than 2.2.1 has this issue, 2.3.0 is the earliest version at which it is observed
    

3. Run `DatastoreMockBeanTest` again - it fails with:

```shell
Error instantiating bean of type  [example.micronaut.DatastoreMockBeanTest]

Message: No such method [allocateId([Lcom.google.cloud.datastore.IncompleteKey;) ] for bean [com.google.cloud.datastore.Datastore]
Path Taken: DatastoreMockBeanTest.datastore
io.micronaut.context.exceptions.BeanInstantiationException: Error instantiating bean of type  [example.micronaut.DatastoreMockBeanTest]

```


#### Note - A Workaround

I can get this to work by using a factory provided for use in the test environment only, which returns the same
mockk<Datastore> (ie bypass @MockBean)

To see this in action

1. Delete the following from `DatastoreMockBeanTest`

   ```kotlin    
   @MockBean(Datastore::class)
    fun mockDatastore(): Datastore {
        return mockk(relaxed = true)
    }
   ```
   

2. In `MockDatastoreFactory`, change `@Requires(env = ["xxx"])` to `@Requires(env = ["test"])`


3. Run `DatastoreMockBeanTest` - it passes
