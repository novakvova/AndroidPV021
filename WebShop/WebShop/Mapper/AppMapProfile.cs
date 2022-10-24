using ApplicationCore.Entities;
using AutoMapper;
using WebShop.Models;

namespace WebShop.Mapper
{
    public class AppMapProfile : Profile
    {
        public AppMapProfile()
        {
            CreateMap<CategoryEntity, CategoryItemModel>()
                .ForMember(x=>x.Image, opt=>opt.MapFrom(x=>$"/images/{x.Image}"));
        }
    }
}
