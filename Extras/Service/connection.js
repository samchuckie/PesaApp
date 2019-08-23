const sql = require('mysql')
const common = require('./common')
const connection = sql.createConnection({
    user: "root",
    password: "password"
}).connect(err =>{
    if(err) throw err;
})
const databaseconnection = sql.createConnection({
    user: "root",
    password: "password",
    database: common.databasename
})


module.exports = {
    connection:connection,
    databaseconnection:databaseconnection
}