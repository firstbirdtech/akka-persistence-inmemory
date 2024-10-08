/*
 * Copyright 2016 Dennis Vriend
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package akka.persistence.inmemory.query

import akka.actor.ExtendedActorSystem
import akka.persistence.inmemory.extension.StorageExtensionProvider
import akka.persistence.query.{javadsl => akkaJavaDsl, scaladsl => akkaScalaDsl, ReadJournalProvider}
import com.typesafe.config.Config

class InMemoryReadJournalProvider(system: ExtendedActorSystem, config: Config) extends ReadJournalProvider {
  override def scaladslReadJournal(): akkaScalaDsl.ReadJournal =
    new scaladsl.InMemoryReadJournal(config, StorageExtensionProvider(system).journalStorage(config))(system)

  override def javadslReadJournal(): akkaJavaDsl.ReadJournal =
    new javadsl.InMemoryReadJournal(scaladslReadJournal().asInstanceOf[scaladsl.InMemoryReadJournal])
}
