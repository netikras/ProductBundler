# Bundler

Bundler is a REST WS bundling together multiple products and suggesting most suitable bundle to the customer. Decision on whether bundle is suitable or not is made by cross-checking bundle requirement rules and customer information. The WS controller has three webmethods:

  - [GET] product
  - [GET] bundle
  - [POST] bundle

  + The first one validates user information and returns a list of products customer can select. 
  + The second one also analyses customer details, selected (preferred) products and suggests most suitable product bundles.
  + The third one - takes the same lis of parameters as the previous one with a small addition - parameter 'bundle'. Method checks whether customer meets required criteria for the bundle to order.

Project structure is modular: each product/bundle is defined in its own class extending an abstract 'Bundle'. The same applies to rules linked to each module, except the abstract is called 'Rule'. To disable a product one can simply remove it from a static Map in Product class.

### Usage

Project has been developed using Eclipse NEO IDE with Gradle integration (on Linux system). There are two easy ways to start the WS up:
   + using Apache Tomcat (localhost/bundler/)
      + copy build/libs/Bundler-1.0.war to ${CATALINA_HOME}/webapps/ directory - if Tomcat is running it should pick the application up.
      + copy __bundler.xml__ to ${CATALINA_HOME}/conf/Catalina/<host>/ directory, update the .war path in it and it should appear in tomcat in a few seconds.
   + using gradle's Jetty plugin (localhost:15680/bundler/)
      + if you have Linux installed - simply run ___gradle jettyRun___ from inside this directory. If you are on windows - use Gradle wrapper instead: ___gradlew jettyRun___. 

To run tests using Gradle: __gradle test__ or __gradlew test__ depending on whether you are on Linux or Windows. Optionally you can use parameter __--info__ to see more verbose information.

