class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

    "/person/$id?"(controller: 'c2', parseRequest: true){
        action = [GET: "showPerson", PUT: "updatePerson", POST: "addPerson", DELETE: "deletePerson"]
    }

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
