import DB from "./database/database.js";

function main() {
	DB.getCategory().then(async (cate) => console.log(await cate.getAll()));
	DB.getCategory().then(async (cate) => console.log(await cate.getById(1)));
}

main();
