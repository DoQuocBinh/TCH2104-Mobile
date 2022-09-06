import {openDB} from 'idb'
import { Customer } from './models/Customer'

const DATABASE_NAME = "CustomerDB"

async function insertCustomer(customerInfo:Customer){
    const db = await openDB(DATABASE_NAME,1)
    const id = await db.put("customers",customerInfo)
    return id
}

async function getAllCustomers(){
    const db = await openDB(DATABASE_NAME,1)
    const result = await db.getAll("customers")
    return result
}
async function getCustomerById(id:number){
    const db = await openDB(DATABASE_NAME,1)
    return await db.get("customers",id)
}

initDB().then(()=>{
    console.log("database created!")
})

async function initDB() {
    const db = await openDB(DATABASE_NAME,1,{
        upgrade(db){
            const store = db.createObjectStore('customers',{
                keyPath: 'id',
                autoIncrement:true
            })
        }
    })
}