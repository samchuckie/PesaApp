const common = require('./common')
const connection =  require('./connection')
//Handle if exist error later
// const create_db_query = `CREATE DATABASE IF NOT EXISTS ${common.databasename} `
const create_db_query = `CREATE DATABASE ${common.databasename} `
const myConnection = connection.connection;
console.log(create_db_query)
myConnection.connect(err =>{
    if (err){
        console.log("Could not make connection");
        throw err
    }
    console.log("Successful in making a connection");
    myConnection.query( create_db_query ,  (err,results) =>{
       if (err) throw err;
       console.log("Database created");
    });
});