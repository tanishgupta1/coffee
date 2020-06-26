Coffee Machine
------------------


## For building the project inside the source code directory, use
</br>
<code>
mvn clean install
</code>
<br></br>

## To run the code, go to the director and run
mvn exec:java -Dexec.mainClass=Application


## To run the test:
mvn test 


## Points to note :
1) Command line will have three options *process_beverages* , *add_ingredients* and *exit*
2) Once you enter *process_beverages* , give file path of sample_coffee json. File path example - /Users/sample/coffee/<filename>.json
3) If you enter *add_ingredients* then give file path of ingredients to be added. File path example - /Users/sample/coffee/<filename>.json
    Format of json example:
     <code>
         {
        "total_items_quantity": {
        "hot_water": 500,
        "hot_milk": 500,
        "ginger_syrup": 100,
         "sugar_syrup": 100,
        "tea_leaves_syrup": 100
        }
    }
     </code>
    
4) If *total_items_quantity* is in the json then it will keep on adding ingredients. This is applicable for both add_ingredients and process_beverages command.
If you don't want to add total_items_quantity everytime then remove total_items_quantity from process_beverages json file.
5) Implementation for beverages Black Tea, Green tea, Coffee and HotTea has been done for now
6) Beverages will be processed in the batch of number of outlets given in the json.
7) Press exit to terminate the process.
   
