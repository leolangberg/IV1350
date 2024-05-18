# IV1350
<h2>How to use</h2>
<h6>Initialize project in Maven by running following commands in root directory (same dir as pom.xml): </h6>
<h6> mvn compile </h6>
<h6> mvn exec:java </h6>
<h2>Notes</h2>
<h6>
  <ul>
    <li>Update Tests to work with Exceptions.</li>
    <li>GoF 'Singleton' used for databases.</li>
    <li>GoF 'Strategy' & 'Composite' used for Discounts.</li>
    <li>Bug where you can pay multiple times in one sale.</li>
  </ul>
</h6>

<!--
<h6>

  <ul>
    <li> Seminar 3 = build normal process + tests -> Seminar 4 build fail process. <li>
    <li>Display -> Printer + View</li>
    <li> Tests </li>
    <li> Discounts + correct process </li>
    <li>rename SalesHandler to PoS? </li>
    <li>View.updateItemQuantity() same as getItem()</li>
    <li>Discount Handling NOT REQUIRED for SEM3</li>
  </ul>
</h6>
-->
<h2>Questions Seminar 4</h2>
<h6>
  <ul>
    <li>Exception polymorphism?</li>
    <li>Exceptions all in one folder? (do Excpetions have to be in same package folder?)<li>
  </ul>
</h6>
<!-- <h3>Appropriate response regarding task2 feedback:</h3>
<ul>
  <li>1. DTO must be read only. SaleDTO has reference to the same itemList created by Sale. This violates MVC, since it means the controller can update itemList without going through the Sale Class.</li>
  <h6>
     <li>Solved by placing a lock on itemlist (so that controller cannot use it).</li>
  </h6>
  <li>2. Controller is doing views job by preparing string that is printed by the view. This violates MVC.</li>
  <h6>
    <li>Solved by introducing ItemPackageDTO that contains ItemDTO, quantity, runningTotalCost, runningTotalVAT which contains all information that View Layer needs to print correct statements. </li>
  </h6>
</ul>
-->
<!-- 
<h3>Further Questions:</h3>
-->
<!--
<ul>
  <li></li>
</ul>
-->

<h5>Sample Run:</h5>

<!-- ![iv1350samplerun](https://github.com/leolangberg/IV1350/assets/152855963/1e1a8c77-fca9-4ad5-b698-d5712a9ee3e6) -->
![seminar4sampleRunSale1](https://github.com/leolangberg/IV1350/assets/152855963/d858eccd-cdaf-462f-ad3c-c7de3bf468f1)
![seminar4sampleRunSale2](https://github.com/leolangberg/IV1350/assets/152855963/b53e45cf-4fe2-4cba-b0fe-0609a6ced0e3)

