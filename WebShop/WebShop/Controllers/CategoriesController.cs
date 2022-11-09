using ApplicationCore.Entities;
using AutoMapper;
using Infrastructure.Data;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using WebShop.Helpers;
using WebShop.Models;

namespace WebShop.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class CategoriesController : ControllerBase
    {
        private readonly ShopEFContext _context;
        private readonly IMapper _mapper;

        public CategoriesController(ShopEFContext context, IMapper mapper)
        {
            _context = context;
            _mapper = mapper;
        }

        [HttpGet]
        
        [Route("list")]
        public async Task<IActionResult> Index()
        {
            var model = _context.Categories
                .Select(x =>_mapper.Map<CategoryItemModel>(x))
                .ToList();
            return Ok(model);
        }

        [HttpPost]
        [Authorize]
        [Route("create")]
        public async Task<IActionResult> Create(CategoryCreateModel model)
        {
            try
            {
                string userName = User.Identity.Name;
                var category = _mapper.Map<CategoryEntity>(model);
                category.Image = ImageWorker.SaveImage(model.Image);
                await _context.Categories.AddAsync(category);
                await _context.SaveChangesAsync();
            }
            catch (Exception ex)
            {
                return BadRequest(new { error = ex.Message });
            }
            return Ok();
        }
    }
}
