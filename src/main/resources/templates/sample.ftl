<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Static Template</title>
    <style>
      * {
        padding: 0;
        margin: 0;
      }
      body {
        font-size: 13px;
        width: 90%;
        margin: 40px auto;
        border: 4px solid grey;
      }
      .titleText {
        font-size: 16px;
        text-align: center;
      }
      .barCode {
        margin: 30px 0;
        padding-left: 10%;
      }
      .primaryAddress {
        display: flex;
        width: 98%;
        //justify-content: space-between;
        margin: auto;
        //margin-bottom: 30px;
      }
      .secondaryAddress {
        display: flex;
        width: 98%;
        justify-content: space-between;
        margin: auto;
      }
      .prLeftNameTxt {
        font-weight: bold;
      }
      .prRightRMTxt {
        font-weight: bold;
        text-decoration: underline;
      }
      .textualInfo {
        padding-left: 10%;
      }
      .table {
        width: 98%;
        margin: auto;
        margin-bottom: 28px;
      }
      .bigTable {
        width: 98%;
        margin: auto;
      }
      table {
        width: 100%;
      }
      .tableHeading {
        text-align: center;
        font-weight: bold;
        font-size: 13px;
        border: 2px solid black;
        //padding: 10px;
      }
      table,
      th,
      td {
        border: 1px solid black;
        border-collapse: collapse;
        text-align: center;
      }
      th,
      td {
        //padding: 15px;
      }
      .head {
        background-color: #b8e2fc;
      }
      .statementHeader {
        display: flex;
        width: 99%;
        justify-content: space-between;
        font-size: 13px;
        border: 2px solid black;
        //padding: 10px;
      }
      .imgHeader {
        width: 100%;
      }
      .imgFooter {
        display: flex;
        width: 90%;
        margin-left: auto;
        margin-right: auto;
      }
      .footerContainer {
          display: flex;
          justify-content: center;
          align-items: center;
          width: 100%
      }
      .prLeft {
      width: 50%;
      padding: 15px 35px;
      }

     .prRight {
      width: 50%;
      float: right;
      //padding: 15px 0px;
      }
    </style>
    <header>
            <img class="imgHeader" src="static/header.png"/>
    </header>
  </head>
  <body >
    <h1 class="titleText">
      YOUR ACCOUNT STATEMENT FROM 02-Nov-2017 TO 30-Nov-2017
    </h1>
    <div class="barCode">Bar code img goes here</div>
    <div class="primaryAddress">
      <div class="prLeft">
        <p class="prLeftNameTxt">${salutation}${primaryHolder}</p>
        <p class="prLeftTxt">${customerInfo.customerAddress.addressLine1}</p>
        <p class="prLeftTxt">${customerInfo.customerAddress.addressLine2}</p>
        <p class="prLeftTxt">${customerInfo.customerAddress.area}</p>
        <p class="prLeftTxt">${customerInfo.customerAddress.state}</p>
        <p class="prLeftTxt">${customerInfo.customerAddress.pinCode}</p>
      </div>
      <div class="prRight">
        <br />
        <p class="prRightRMTxt">RM Details</p>
        <p class="prRightTxt">KARISHANA NAHED</p>
        <p class="prRightTxt">SUDTDH ENDKS</p>
        <p class="prRightTxt">DELHI</p>
        <p class="prRightTxt">110283</p>
      </div>
    </div>
    <div class="secondaryAddress">
      <div class="prLeft">
        <p><span class="prLeftNameTxt">EMAIL ID: </span>${customerInfo.emailId}</p>
        <p><span class="prLeftNameTxt">MOBILE NO.: </span>${customerInfo.mobileNumber}</p>
      </div>
      <div class="prRight">
        <p>Customer care detail - calk dus indoe 1800 1200</p>
        <p>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          calk dus indore +627929827929
        </p>
      </div>
    </div>
    <p class="textualInfo">
      In case of any mismatch in terms of contact details updated with us,
      kindly visit the nearest branch to update the latest contact details
    </p>
    <div class="table">
      <div class="tableHeading">
        Account Relationship Summary as on 30-Nov-2017
      </div>
      <table>
        <tr>
          <th class="head">Account Number</th>
          <th class="head">Account Type</th>
          <th class="head">Currency</th>
          <th class="head">Balance</th>
        </tr>
        <tr>
          <td>${accountNumber}</td>
          <td>${accountType}</td>
          <td>${currency}</td>
          <td>${balance}</td>
        </tr>
      </table>

    </div>
    <div class="bigTable">
      <div class="tableHeading">
        Statement Of Transactions In Savings/Current Account No:000481300001957
        For The Period Of 02-Nov-2017 to 30-Nov-2017
      </div>
       <div class="statementHeader">
        <div class="prLeft">
          <p><span class="prLeftNameTxt">Primary Holder :</span>${salutation}${primaryHolder}
          <p><span class="prLeftNameTxt">Joint Holder 1 :</span> NA</p>
          <p><span class="prLeftNameTxt">Joint Holder 2:</span> NA</p>
          <p><span class="prLeftNameTxt">Nominee :</span>${nomineeInfo.nomineeName}</p>
        </div>
        <div class="prRight">
          <br />
          <p><span class="prLeftNameTxt">Product Code :</span>${productCode}</p>
          <p><span class="prLeftNameTxt">A/C opening Date :</span>${accountOpeningDate}</p>
          <p><span class="prLeftNameTxt">Account Variation/Description :</span>${accountType}</p>
          <p><span class="prLeftNameTxt">Curreny :</span>${currency}</p>
          <p><span class="prLeftNameTxt">Account Status :</span>${accountStatus}</p>
          <p><span class="prLeftNameTxt">OD Limit :</span>${overdraftLimit}</p>
          <p><span class="prLeftNameTxt">Available Balance :</span>${balance}</p>
          <p><span class="prLeftNameTxt">Sweep In :</span>No</p>
        </div>
      </div>
      <table>
        <tr>
          <th class="head">Transaction Date</th>
          <th class="head">Value Date</th>
          <th class="head">Description</th>
          <th class="head">Cheque No</th>
          <th class="head">Deposits</th>
          <th class="head">Withdrawals</th>
          <th class="head">Balance</th>
        </tr>
       <#list transactionInfoList as transaction>
       <tr>
         <td>${transaction.transactionDate}</td>
         <td>${transaction.valueDate}</td>
         <td>${transaction.description}</td>
         <td>${transaction.refNo}</td>
         <td>${transaction.depositedAmount}</td>
         <td>${transaction.withdrawnAmount}</td>
         <td>${transaction.runningBalance}</td>
        </tr>
       </#list>
      </table>
    </div>
  </body>
  <footer class="footerContainer">
    <img class="imgFooter" src="static/footer.png"/>
  </footer>
</html>