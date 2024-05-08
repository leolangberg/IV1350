# IV1350
<h2>How to use</h2>
<h6>Initialize project in Maven by running following commands in root directory (same dir as pom.xml): </h6>
<h6> mvn compile </h6>
<h6> mvn exec:java </h6>
<h2>Notes</h2>
<h6>
  <ul>
    <li> Add Unit Test to prove 1. <li>
    <li>"reference to same itemList" meaning that to create new instance or to just lock it?</li>
    <li></li>
    <li>1. is solved by placing a lock on itemlist (so that controller cannot use it).</li>
    <li>2. is solved by introducing ItemPackageDTO that contains ItemDTO, quantity, runningTotalCost, runningTotalVAT which contains all information that View Layer needs to print correct statements. </li>
  </ul>
</h6>
<h2>Questions</h2>
<h3>Appropriate response regarding task3 feedback:</h3>
<ul>
  <li>1. DTO must be read only. SaleDTO has reference to the same itemList created by Sale. This violates MVC, since it means the controller can update itemList without going through the Sale Class.</li>
  <li>2. Controller is doing views job by preparing string that is printed by the view. This violates MVC.</li>
</ul>

<h3>Further Questions:</h3>

<ul>
  <li></li>
</ul>

<h5>Sample Run:</h5>

![iv1350samplerun](https://github.com/leolangberg/IV1350/assets/152855963/1e1a8c77-fca9-4ad5-b698-d5712a9ee3e6)

