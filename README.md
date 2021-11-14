# Multiple-Producer-Consumer
### Author: Aaron Jones

This is the repoistory for the optional Multiple Producer Consumer problem.  The MPC problem runs multiple synchronized producer and consumer threads.  The example developed here is a mock-up restaurant.

Included in the repository is a .jar file, and two example input files.  The program can be run by placing the .jar file and one of the .txt input files in the same directory, and typing `java -jar MultipleProducerConsumer.jar` in a command line window.  The program will then prompt for the name of the input file; enter it either with or without the .txt extension.

The program will then run the MPC problem from the given inputs

## Expected Input Format
The program expects the first line of the input file to be the number of `Waiters`.  Each successive line contains the following (in the specified order), using spaces as a delimiter:
- The name of the `Waiter`
- The number of `Customers` served by the first `Waiter`
- The name of the first `Customer`
- Three separate courses to be served to the first `Customer`
- The name of the second `Customer` served by the first `Waiter`
- Three separate courses to be served to the first `Customer`
- ...

Variations from this format may cause the program to terminate or output otherwise unexpected behavior.

## Example Output
Below is an example output from running the provided `minions.txt` input file.  Other outputs may vary.  Each course will always be served in order, and each `Customer` will only eat after having been served.

```
PS C:\Users\jones\Desktop> java -jar MultipleProducerConsumer.jar
Enter the name of the file to test: minions
Dave serves Stuart calamari
Bob serves Phil bread
Josh serves Steve soup
Phil is eating bread
Stuart is eating calamari
Steve is eating soup
Bob serves Jerry chips_and_salsa
Jerry is eating chips_and_salsa
Dave serves Kevin bruschetta
Kevin is eating bruschetta
Dave serves Stuart pasta
Dave serves Kevin chicken_marsala
Kevin is eating chicken_marsala
Stuart is eating pasta
Josh serves Mark sandwich
Mark is eating sandwich
Josh serves Mike scone
Mike is eating scone
Bob serves Carl salad
Carl is eating salad
Josh serves Chris wontons
Chris is eating wontons
Bob serves Phil pizza
Phil is eating pizza
Dave serves Stuart banana_ice_cream
Stuart is eating banana_ice_cream
Josh serves Steve steak
Steve is eating steak
Josh serves Mark chicken_wings
Mark is eating chicken_wings
Dave serves Kevin banana_pudding
Kevin is eating banana_pudding
Bob serves Jerry tacos
Jerry is eating tacos
Bob serves Carl fish
Carl is eating fish
Josh serves Mike fish_and_chips
Mike is eating fish_and_chips
Bob serves Phil banana_bread
Phil is eating banana_bread
Josh serves Chris noodles
Chris is eating noodles
Bob serves Jerry banana_flan
Jerry is eating banana_flan
Josh serves Steve chocolate_gelato
Steve is eating chocolate_gelato
Bob serves Carl banana_cheesecake
Carl is eating banana_cheesecake
Josh serves Mark strawberry_gelato
Mark is eating strawberry_gelato
Josh serves Mike blackberry_gelato
Mike is eating blackberry_gelato
Josh serves Chris cake
Chris is eating cake
```
