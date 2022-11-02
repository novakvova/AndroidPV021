namespace WebShop.Models
{
    public class CategoryItemModel
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Image { get; set; }
    }

    public class CategoryCreateModel
    {
        public string Name { get; set; }
        public string Image { get; set; }
    }
}
