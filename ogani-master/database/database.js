import Category from "./Category.js";

class DataBase {
	#category;
	#product;
	#order;

	constructor() {}

	async getCategory() {
		if (!this.#category) {
			this.#category = new Category();
			await this.#category.init();
		}
		return this.#category;
	}
}

export default new DataBase();
