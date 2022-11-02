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

            CreateMap<CategoryCreateModel, CategoryEntity>()
                .ForMember(x => x.DateCreated, opt => opt.MapFrom(x=> DateTime.SpecifyKind(DateTime.Now, DateTimeKind.Utc)))
                .ForMember(x => x.Image, opt => opt.Ignore());
        }
    }
}
