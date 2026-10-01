.PHONY: dependencies clean test package help

dependencies:
	mvn dependency:tree

clean:
	mvn clean

test:
	mvn clean test -Dapp.dbfile=`pwd`/pokemon_wiki.db

package:
	mvn clean package -Dapp.dbfile=`pwd`/pokemon_wiki.db

help:
	@echo 'make dependencies - Show the dependencies'
	@echo 'make clean - Clean the project'
	@echo 'make test - Run the tests'
	@echo 'make package - Package the project'
	@echo 'make help - Show this help message'
