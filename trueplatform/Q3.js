main("susan");
function main(query) {
	let sources = [
		{
			id: 1,
			name: "David",
			title: "Marketing Officer",
		},
		{ id: 2, name: "Susan", title: "Software Engineer" },
		{ id: 3, name: "Mike", title: "Assistant to Management Board" },
	];

	// Apply
	let ds = new DataSearchV03();
	ds.init(sources);
	let result = ds.suggest(query);

	console.log(result);
}

// ** solution v0.1 **

class DataSearchV01 {
	source;

	init(source) {
		this.source = source;
	}

	/**
	 * @param {string} query
	 */
	suggest(query) {
		const lowerCaseQuery = query.toLowerCase();

		return this.source.filter(
			(item) =>
				item.name.toLowerCase().startsWith(lowerCaseQuery) ||
				item.title.toLowerCase().startsWith(lowerCaseQuery)
		);
	}
}

// ** solution v0.2 **

class DataSearchV02 {
	source;

	// dictionary of tokens
	index = {};

	init(source) {
		this.source = source;

		for (let i = 0; i < source.length; i++) {
			let item = source[i];
			const tokens = [
				...this.#tokenize(item.name),
				...this.#tokenize(item.title),
			];

			for (let token of tokens) {
				if (!this.index[token]) {
					this.index[token] = [];
				}

				this.index[token].push(i);
			}
		}
	}

	/**
	 * @param {string} query
	 */
	suggest(query) {
		const lowerCaseQuery = query.toLowerCase();

		const tokensQuery = this.#tokenize(lowerCaseQuery);
		const indexResultSet = new Set();

		for (let token of tokensQuery) {
			if (this.index[token]) {
				for (let i of this.index[token]) {
					indexResultSet.add(i);
				}
			}
		}

		return Array.from(indexResultSet).map((i) => this.source[i]);
	}

	#tokenize(str) {
		return str.toLowerCase().match(/\w+/g) || [];
	}
}

// ** solution v0.3 **

class DataSearchV03 {
	source;

	// dictionary of tokens
	index = {};

	init(source) {
		this.source = source;

		for (let i = 0; i < source.length; i++) {
			let item = source[i];
			// process for name
			let firstSubString = "";
			for (let charater of item.name.toLowerCase()) {
				firstSubString = firstSubString.concat(charater);

				if (!this.index[firstSubString]) {
					this.index[firstSubString] = [];
				}

				this.index[firstSubString].push(i);
			}

			// process for title
			firstSubString = "";
			for (let charater of item.title.toLowerCase()) {
				firstSubString = firstSubString.concat(charater);

				if (!this.index[firstSubString]) {
					this.index[firstSubString] = [];
				}

				this.index[firstSubString].push(i);
			}
		}
	}

	/**
	 * @param {string} query
	 */
	suggest(query) {
		const lowerCaseQuery = query.toLowerCase();

		return Array.from(this.index[lowerCaseQuery]).map((i) => this.source[i]);
	}
}
