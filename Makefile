.PHONY: dependencies clean test package help

dependencies:
	./mvnw dependency:tree

clean:
	./mvnw clean

test:
	./mvnw clean test -Dapp.dbfile=`pwd`/pokemon_wiki.db

package:
	./mvnw clean package -Dapp.dbfile=`pwd`/pokemon_wiki.db

run:
	java -Dapp.dbfile=`pwd`/pokemon_wiki.db -jar pokemon-wiki-web/target/pokemon-wiki-web.jar

help:
	@echo 'make dependencies - Show the dependencies'
	@echo 'make clean - Clean the project'
	@echo 'make test - Run the tests'
	@echo 'make package - Package the project'
	@echo 'make run - Run the project'
	@echo 'make help - Show this help message'
