const common = require('./common')
const connection =  require('./connection')
//Store passwords in safer,secure format
//Describe table to change its structure later
const select_All = `select * from arts union select * from gamings union select * from featureds`
// const select_All = `select * (select * from arts union select * from gamings) as b where b.title in (select * from favourites)`


const selectall = (resp) => {
    const dbaccess = connection.databaseconnection
    dbaccess.connect(err =>{
    if(err) throw err
    dbaccess.query(select_All , (err,results)=>{
        if(err) throw err
        console.log(results)
        resp.send(results)
        // return err
    })
})
}
module.exports = {
    sall:selectall
}