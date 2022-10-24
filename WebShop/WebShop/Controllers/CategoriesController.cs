using AutoMapper;
using Infrastructure.Data;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
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
    }
}
