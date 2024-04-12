
export const ProductGalleryCard = ({imagelink, url, title, price}: {imagelink: string, url: string, title: string, price: string}) => {
    return (
        <div className='ProductCard'>
                 <a href={url}>
                <img src={imagelink}/>
                </a>
                <h4 className="ProductCardTitle">{title}</h4>
                <h5 className="ProductCardPrice">{price}</h5>
        </div>
    )
}
