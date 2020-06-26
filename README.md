Coffee Machine
------------------


For building the project inside the source code directory, use
</br>
<code>
mvn clean install
</code>
<br></br>

To run the code, go to the directory, and run

    <code>
     mvn exec:java -Dexec.mainClass=Application
    </code>

     Inside arguments pass json file path as input.
</br>

To run test:
mvn test


Points to note :

1) The Parking Lot will be created once in the whole lifecycle of the application. Once it's created, and if another request for the same comes, the application will show
    </br>
    <code>
    Parking Lot Exists
    </code>
   </br></br>
2) If unparking is tried on an empty slot, you will see following message
   </br>
   <code>
   Slot is free
   </code>
   </br></br>
3) If unparking is tried for a slot that does not exist, you will see following message
    </br>
    <code>
    Check if slot exists.
    </code>

</br>
</br>
For any incomplete command, you will get an error message
</br>
<code>
Incomplete params. Check parameters and input properly.
</code>
