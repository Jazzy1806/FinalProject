package com.skilldistillery.treattracker.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.treattracker.entities.Ingredient;
import com.skilldistillery.treattracker.entities.Product;
import com.skilldistillery.treattracker.entities.ProductComment;
import com.skilldistillery.treattracker.entities.ProductReport;
import com.skilldistillery.treattracker.entities.Store;
import com.skilldistillery.treattracker.entities.User;
import com.skilldistillery.treattracker.repositories.IngredientRepository;
import com.skilldistillery.treattracker.repositories.ProductCommentRepository;
import com.skilldistillery.treattracker.repositories.ProductReportRepository;
import com.skilldistillery.treattracker.services.AuthService;
import com.skilldistillery.treattracker.services.IngredientService;
import com.skilldistillery.treattracker.services.ProductCommentService;
import com.skilldistillery.treattracker.services.ProductReportService;
import com.skilldistillery.treattracker.services.ProductService;
import com.skilldistillery.treattracker.services.StoreService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class ProductController {

	@Autowired
	private ProductService prodService;

	@Autowired
	private ProductReportService prodReportService;

	@Autowired
	private StoreService storeService;

	@Autowired
	private ProductReportRepository prodReportRepo;

	@Autowired
	private IngredientRepository ingredientRepo;

	@Autowired
	private IngredientService ingredientService;

	@Autowired
	private AuthService authService;

	@Autowired
	private ProductCommentService commentService;

	@Autowired
	private ProductCommentRepository commentRepo;

	/*
	 * -------- Products --------
	 */

	@GetMapping("products")
	public List<Product> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		return prodService.index(principal.getName());
	}

	@GetMapping("products/{pid}")
	public Product show(@PathVariable int pid, HttpServletRequest req, HttpServletResponse res, Principal principal) {
		Product product = prodService.show(principal.getName(), pid);
		if (product == null) {
			res.setStatus(404);
		}
		return product;
	}

	@GetMapping("products/keyword/{keyword}")
	public List<Product> findByKeyword(@PathVariable String keyword, HttpServletRequest req, HttpServletResponse res,
			Principal principal) {
		return prodService.findByKeyword(principal.getName(), keyword);
	}

	@PostMapping("products")
	public Product create(@RequestBody Product product, HttpServletRequest req, HttpServletResponse res,
			Principal principal) {
		User user = authService.getUserByUsername(principal.getName());
		Product created = null;
		if (user != null) {
			try {
				created = prodService.create(principal.getName(), product);
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(created.getId());
				res.setHeader("Location", url.toString());
			} catch (Exception e) {
				e.printStackTrace();
				res.setStatus(400);
			}
		}
		return created;
	}

	@PutMapping("products/{pid}")
	public Product update(@PathVariable int pid, @RequestBody Product prodUpdate, HttpServletRequest req,
			HttpServletResponse res, Principal principal) {
		User user = authService.getUserByUsername(principal.getName());
		Product updated = null;
		if (user != null) {
			try {
				updated = prodService.update(principal.getName(), pid, prodUpdate);
				if (updated == null) {
					res.setStatus(404);
				}
				res.setHeader("Location", req.getRequestURL().toString());
			} catch (Exception e) {
				e.printStackTrace();
				res.setStatus(400);
			}
		}
		return updated;
	}

	@DeleteMapping("products/{pid}")
	public Boolean destroy(@PathVariable int pid, HttpServletRequest req, HttpServletResponse res,
			Principal principal) {
		Boolean deleted = prodService.destroy(principal.getName(), pid);
		User user = authService.getUserByUsername(principal.getName());
		if (user != null) {
			if (deleted) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		}
		return deleted;
	}

	/*
	 * /* -------- Reports --------
	 */

	// GET /products/{productId}/reports find user updates about product
	@GetMapping("products/{pid}/reports")
	public List<ProductReport> getProductReports(@PathVariable int pid, HttpServletRequest req, HttpServletResponse res,
			Principal principal) {
		User user = authService.getUserByUsername(principal.getName());
		List<ProductReport> reports = prodReportRepo.findByProductId(pid);

		if (user != null) {
			if (reports == null) {
				res.setStatus(404);
			}
		}
		return reports;
	}

	// GET /products/{productId}/reports/{reportId} find specific report
	@GetMapping("products/{pid}/reports/{rid}")
	public ProductReport getProductReport(@PathVariable int pid, @PathVariable int rid, HttpServletRequest req,
			HttpServletResponse res, Principal principal) {
		User user = authService.getUserByUsername(principal.getName());
		if (user != null) {
			Product product = prodService.show(principal.getName(), pid);
			if (product == null) {
				res.setStatus(404);
			} else {
				ProductReport report = prodReportRepo.findByIdAndProductId(rid, product.getId());
				if (report == null) {
					res.setStatus(404);
				}
				return report;
			}

		}
		return null;
	}

	// POST /products/{productId}/reports create report
	@PostMapping("products/{pid}/stores/{sid}/reports")
	public ProductReport createReport(@PathVariable int pid, @PathVariable int sid, @RequestBody ProductReport report,
			HttpServletRequest req, HttpServletResponse res, Principal principal) {
		User user = authService.getUserByUsername(principal.getName());
		Store store = storeService.findStorebyId(sid, principal.getName());
		ProductReport created = null;

		if (user != null & store != null) {
			try {
				created = prodReportService.create(principal.getName(), report);
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(created.getId());
				res.setHeader("Location", url.toString());
			} catch (Exception e) {
				e.printStackTrace();
				res.setStatus(400);
			}
		}
		return created;
	}

	// PUT /products/{productId}/reports/{reportId} update specific report
	@PutMapping("products/{pid}/reports/{rid}")
	public ProductReport updatReport(@PathVariable int pid, @PathVariable int rid,
			@RequestBody ProductReport reportUpdate, HttpServletRequest req, HttpServletResponse res,
			Principal principal) {
		User user = authService.getUserByUsername(principal.getName());
		Product product = prodService.show(principal.getName(), pid);
		ProductReport updated = null;

		if (user != null && product != null) {
			try {
				updated = prodReportService.update(principal.getName(), rid, reportUpdate);
				if (updated == null) {
					res.setStatus(404);
				}
				res.setHeader("Location", req.getRequestURL().toString());
			} catch (Exception e) {
				e.printStackTrace();
				res.setStatus(400);
			}
		}
		return updated;
	}

	// DELETE /products/{productId}/reports/{reportId} delete specific report//
	@DeleteMapping("products/{pid}/reports/{rid}")
	public Boolean destroyReport(@PathVariable int rid, HttpServletRequest req, HttpServletResponse res,
			Principal principal) {
		Boolean deleted = prodReportService.destroy(principal.getName(), rid);
		User user = authService.getUserByUsername(principal.getName());
		if (user != null) {
			if (deleted) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		}
		return deleted;
	}

	/*
	 * -------- Ingredients --------
	 */

	// GET /products/{productId}/ingredients find all ingredients
	@GetMapping("products/{pid}/ingredients")
	public List<Ingredient> getIngredients(@PathVariable int pid, HttpServletRequest req, HttpServletResponse res,
			Principal principal) {
		User user = authService.getUserByUsername(principal.getName());
//		List<Ingredient> ingredients = ingredientRepo.findByProducts_ProductId(pid);
		List<Ingredient> ingredients = ingredientRepo.findByProductsId(pid);

		if (user != null) {
			if (ingredients == null) {
				res.setStatus(404);
			}
		}
		return ingredients;
	}

	// GET /products/{productId}/ingredients/{ingId} find specific ingredient
	@GetMapping("products/{pid}/ingredients/{iid}")
	public Ingredient getIngredient(@PathVariable int pid, @PathVariable int iid, HttpServletRequest req,
			HttpServletResponse res, Principal principal) {
		User user = authService.getUserByUsername(principal.getName());
		if (user != null) {
			Product product = prodService.show(principal.getName(), pid);
			if (product == null) {
				res.setStatus(404);
			} else {
				Ingredient ingredient = ingredientRepo.findByIdAndProductsId(iid, product.getId());
				if (ingredient == null) {
					res.setStatus(404);
				}
				return ingredient;
			}

		}
		return null;
	}
	// POST /products/{productId}/ingredients create ingredient

	// POST /products/{productId}/reports create report
	@PostMapping("products/{pid}/ingredients")
	public Ingredient createIngredient(@PathVariable int pid, @RequestBody Ingredient ingredient,
			HttpServletRequest req, HttpServletResponse res, Principal principal) {
		User user = authService.getUserByUsername(principal.getName());
//		Store store = storeService.findStorebyId(sid, principal.getName());
		Ingredient created = null;

		if (user != null) {
			try {
				created = ingredientService.create(principal.getName(), ingredient);
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(created.getId());
				res.setHeader("Location", url.toString());
			} catch (Exception e) {
				e.printStackTrace();
				res.setStatus(400);
			}
		}
		return created;
	}

	// PUT /products/{productId}/ingredients/{ingId} update specific
	// ingredient-admin/ store only
	@PutMapping("products/{pid}/ingredients/{iid}")
	public Ingredient updatIngredient(@PathVariable int pid, @PathVariable int iid, @RequestBody Ingredient ingredient,
			HttpServletRequest req, HttpServletResponse res, Principal principal) {
		User user = authService.getUserByUsername(principal.getName());
		Product product = prodService.show(principal.getName(), pid);
		Ingredient updated = null;
		if (user != null && product != null) {
			try {
				updated = ingredientService.update(principal.getName(), iid, ingredient);
				if (updated == null) {
					res.setStatus(404);
				}
				res.setHeader("Location", req.getRequestURL().toString());
			} catch (Exception e) {
				e.printStackTrace();
				res.setStatus(400);
			}
		}
		return updated;
	}

	// DELETE /products/{productId}/ingredient/{ingId} delete specific ing-ad/only
	/*
	 * This is only removing a single ingredient for now Ingredient destroy has been
	 * written needs to be modified to removed from all Products containing it first
	 */
	@DeleteMapping("products/{pid}/ingredients/{iid}")
	public Boolean removeIngredient(@PathVariable int pid, @PathVariable int iid, HttpServletRequest req,
			HttpServletResponse res, Principal principal) {
		Product product = prodService.show(principal.getName(), pid);
		Ingredient ingredient = ingredientRepo.findByIdAndProductsId(iid, product.getId());
		User user = authService.getUserByUsername(principal.getName());

		if (user != null) {
			product.removeIngredient(ingredient);
			prodService.update(principal.getName(), pid, product);
//			Boolean deleted = ingredientService.destroy(principal.getName(), iid);
//			if (deleted) {
//				res.setStatus(204);
//			} else {
//				res.setStatus(404);
//			}
		}
		return true;
	}

	/*
	 * -------- ProductComments --------
	 */

	// GET /products/{productId}/comments find all comments
	@GetMapping("products/{pid}/comments")
	public List<ProductComment> getProductComments(@PathVariable int pid, HttpServletRequest req,
			HttpServletResponse res, Principal principal) {
		User user = authService.getUserByUsername(principal.getName());
		List<ProductComment> comments = commentRepo.findByProductId(pid);

		if (user != null) {
			if (comments == null) {
				res.setStatus(404);
			}
		}
		return comments;
	}

	// GET /products/{productId}/comments/{commentId} find specific comment
	// Returns the comment found and it's children replies
	@GetMapping("products/{pid}/comments/{pcid}")
	public ProductComment getProductComment(@PathVariable int pid, @PathVariable int pcid, HttpServletRequest req,
			HttpServletResponse res, Principal principal) {
		User user = authService.getUserByUsername(principal.getName());
		if (user != null) {
			Product product = prodService.show(principal.getName(), pid);
			if (product == null) {
				res.setStatus(404);
			} else {
				ProductComment comment = commentRepo.findByIdAndProductId(pcid, pid);
				if (comment == null) {
					res.setStatus(404);
				}
				return comment;
			}

		}
		return null;
	}

	// POST /products/{productId}/comments create comment
	// Parent (first) comment
	@PostMapping("products/{pid}/stores/{sid}/comments")
	public ProductComment createComment(@PathVariable int pid, @PathVariable int sid,
			@RequestBody ProductComment comment, HttpServletRequest req, HttpServletResponse res, Principal principal) {
		User user = authService.getUserByUsername(principal.getName());
		Store store = storeService.findStorebyId(sid, principal.getName());
		ProductComment newComment = null;

		if (user != null & store != null) {
			try {
				newComment = commentService.create(principal.getName(), comment);
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(newComment.getId());
				res.setHeader("Location", url.toString());
			} catch (Exception e) {
				e.printStackTrace();
				res.setStatus(400);
			}
		}
		return newComment;
	}

	// Reply comment
	@PostMapping("products/{pid}/stores/{sid}/comments/{pcid}")
	public ProductComment createCommentReply(@PathVariable int pid, @PathVariable int sid, @PathVariable int pcid,
			@RequestBody ProductComment comment, HttpServletRequest req, HttpServletResponse res, Principal principal) {
		User user = authService.getUserByUsername(principal.getName());
		Store store = storeService.findStorebyId(sid, principal.getName());
		Product product = prodService.show(principal.getName(), pid);
		ProductComment parentComment = commentService.show(principal.getName(), pcid);
		ProductComment replyComment = null;

		if (user != null & store != null & product != null & parentComment != null) {
			replyComment = new ProductComment();
			try {
				replyComment = commentService.create(principal.getName(), comment);
				replyComment.setParentProductComment(parentComment);

				if (commentService.update(principal.getName(), pcid, replyComment) == null) {
					res.setStatus(404);
				}
				res.setStatus(201);
//				StringBuffer url = req.getRequestURL();
//				url.append("/").append(replyComment.getId());
//				res.setHeader("Location", url.toString());
			} catch (Exception e) {
				e.printStackTrace();
				res.setStatus(400);
			}
		}
		return replyComment;
	}

	// PUT /products/{productId}/comments/{commentId} update comment
	@PutMapping("products/{pid}/comments/{cid}")
	public ProductComment updateComment(@PathVariable int pid, @PathVariable int cid,
			@RequestBody ProductComment commentUpdate, HttpServletRequest req, HttpServletResponse res,
			Principal principal) {
		User user = authService.getUserByUsername(principal.getName());
		Product product = prodService.show(principal.getName(), pid);
		ProductComment updated = null;

		if (user != null && product != null) {
			try {
				updated = commentService.update(principal.getName(), cid, commentUpdate);
				if (updated == null) {
					res.setStatus(404);
				}
				res.setHeader("Location", req.getRequestURL().toString());
			} catch (Exception e) {
				e.printStackTrace();
				res.setStatus(400);
			}
		}
		return updated;
	}

	// DELETE /products/{productId}/comments/{commentId} delete comment
//	@DeleteMapping("products/{pid}/comments/{cid}")
//	public Boolean destroyComment(@PathVariable int cid, HttpServletRequest req, HttpServletResponse res,
//			Principal principal) {
//		User user = authService.getUserByUsername(principal.getName());
//		Boolean deleted = commentService.destroy(principal.getName(), cid);
//		if (user != null) {
//			if (deleted) {
//				res.setStatus(204);
//			} else {
//				res.setStatus(404);
//			}
//		}
//		return deleted;
//	}

}