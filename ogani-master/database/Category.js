class Category {
	categories;

	async init() {
		let response = await fetch("./data/categories.json");
		this.categories = response.json();
	}

	async getById(id) {
		let cate = await this.categories;

		return cate.find((cate) => cate.id === id);
	}

	async getAll() {
		return await this.categories;
	}
}

export default Category;
