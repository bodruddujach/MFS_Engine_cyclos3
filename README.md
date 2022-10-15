# mfs-core
Open Source Mobile Banking Core platform based on Cyclos 3.7.3.


#Feature List: 

#API: 

Wallet/Account:	

Base_URL/v1/api/account/register

Registration: 
{
    "walletNo":"01674242987",
    "fullName":"Cust 1",
    "mobile":"01674242987",
    "accountType":"CUSTOMER"/AGENT/DISTRIBUTOR/MERCHANT
    "pin":"2468"
}
Response:
{
    "status": "SUCCESS",
    "walletNo": "01674242987"
}


Activation:
Method: PUT
http://localhost:8089/mfs-core/rest/v1/api/account/activate
{
    "walletNo":"01674242980"
}

Status Update:
Method: PUT
http://localhost:8089/mfs-core/rest/v1/api/account/status
{
    "walletNo":"01674242980",
    "walletStatus":"SUSPENDED"
}


Check PIN:
Method:POST
http://localhost:8089/mfs-core/rest/v1/api/account/check/pin
{
    "walletNo":"01674242986",
    "pin":"2468"
}
Error Response:
{
    "code": "401",
    "message": "Invalid credentials"
}
Success Response:
200OK
{
    "walletNo": "01674242986"
}
 
 
Wallet Information:
Method: GET
http://localhost:8089/mfs-core/rest/v1/api/account/info/01674242986
Response:
{
    "status": "SUCCESS",
    "walletNo": "01674242986",
    "name": "Azom Shah",
    "userType": "CUSTOMER",
    "accountStatus": "ACTIVE"
}
 

Balance Check:
Method:GET
http://localhost:8089/mfs-core/rest/v1/api/account/balance/01674242986

Response:
{
    "status": "SUCCESS",
    "balance": 495.000000,
    "availableBalance": 495.000000
}


Statement:
Method:POST
http://localhost:8089/mfs-core/rest/v1/api/account/statement
{
    "walletNo":"01674242986"
}




#Transaction: 

Method: POST
http://localhost:8089/mfs-core/rest/v1/api/txn
{
    "fromAc":"SYSTEM",  //  MFS MASTER AC
    "toAc":"01674242980", // DISTRIBUTOR
    "txnType":"BANK_CASHIN",
    "amount":1000
}
 
{
    "fromAc":"01674242980", // DISTRIBUTOR
    "toAc":"01674242981", // AGENT
    "txnType":"TOPUP_AGENT",
    "Amount":1000,
    “pin”:”2468”
 
}
{
    "fromAc":"01674242981", // FROM -> AGENT
    "toAc":"01674242986", // CUSTOMER
    "txnType":"CASH_IN_FROM_MFS_AGENT",
    "pin":"2468",
    "amount":1000
}

http://localhost:8089/mfs-core/rest/v1/api/txn
{
    "fromAc":"01674242986", // CUSTOMER
    "toAc":"01674242987", //CUSTOMER
    "txnType":"SEND_MONEY",
    "pin":"2468",
    "amount":500
}
{
    "fromAc":"01674242986",
    "toAc":"01674242981",
    "txnType":"CASH_OUT_TO_MFS_AGENT",
    "pin":"2468",
    "amount":100

}

Distributor Bank cash Out:
{
    "fromAc":"01674242980",
    "toAc":"SYSTEM",
    "txnType":"BANK_CASHOUT",
    "amount":100,
    "pin":"2468"

}


{
    "fromAc":"01674242981", // agent
    "toAc":"01674242980", // distributor
    "txnType":"CASHOUT_AGENT_DISTRIBUTOR",
    "pin":"2468",
    "amount":100

}

MerchantPayment:
{
   "fromAc":"01674242986",
   "toAc":"01674242982",
   "txnType":"PAYMENT",
   "pin":"2468",
   "amount":100
}

Bill Payment
{
   "fromAc":"01674242986",
   "toAc":"01674242983",
   "txnType":"BILL_PAYMENT",
   "pin":"2468",
   "amount":100
}


Mobile Recharge:
{
   "fromAc":"01674242986",
   "toAc":"01674242984",
   "txnType":"MOBILE_RECHARGE",
   "pin":"2468",
   "amount":100
}


Txn Estimate:(fee)
Method:POST
{{mfs_base}}/mfs-core/rest/v1/api/txn/estimate

Request:
{
   "fromAc":"01674242986",
   "toAc":"01674242981",
   "txnType":"CASH_OUT_TO_MFS_AGENT",
   "pin":"2468",
   "amount":1000
}
Response:
{
   "amount": 1000,
   "fee": 18.000000,
   "commission": 5.000000
}


#Txn Detail:
Method:GET
{{mfs_base}}/mfs-core/rest/v1/api/txn/detail/CB27FB7CFC

Response:
{
   "walletStatementDetailList": [
       {
           "id": 5, //parent transaction
           "amount": 1000.000000,
           "fromWallet": "01674242981",
           "fromName": "5 - 01674242981 (Agent1)",
           "toWallet": "01674242986",
           "toName": "3 - 01674242986 (Azom Shah)",
           "typeId": 34,
           "transactionNumber": "CB27FB7CFC",
           "typeName": "Customer Cash In Agent Point",
           "canReverse": true,
           "description": "Customer Cash In Agent Point. From Ac: 01674242981, To Ac:01674242986"
       },
       {
           "id": 6,
           "parentId": 5, // child transaction
           "amount": 5.000000,
           "fromWallet": "Agent Commission Cash In",
           "fromName": "System",
           "toWallet": "01674242981",
           "toName": "5 - 01674242981 (Agent1)",
           "typeId": 35,
           "transactionNumber": "B7AC9E8C9F",
           "typeName": "Agent Commission transaction CashIn",
           "canReverse": true,
           "description": "Agent Commission transaction CashIn"
       }
   ]
}





