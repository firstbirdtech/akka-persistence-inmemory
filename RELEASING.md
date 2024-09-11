# Releasing

To release a new version of akka-persistence-inmemory follow the following steps:

1. Make sure all changes are commited to the master branch and that the builds successfully passed
2. Add copyright banner to new files, or update headers where applicable. IMPORTANT: Do not remove existing copyright
   attributions!
3. Use git to tag the lastest master commit with the new version: `git tag -a vx.x.x -m "vx.x.x"`
4. Push local git tag to remote using `git push --follow-tags`
5. Wait until [GitHub Action](https://github.com/firstbirdtech/akka-persistence-inmemory/actions?query=workflow%3ACI) finished the publish job
6. Create a new release in [GitHub](https://github.com/firstbirdtech/akka-persistence-inmemory/releases)