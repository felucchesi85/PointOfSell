package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.ProductDto;
import com.abm.pos.com.abm.pos.dto.TransactionLineItemDto;
import com.abm.pos.com.abm.pos.util.SQLQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asp5045 on 5/24/16.
 */
@Component
public class ProductManager
{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SQLQueries sqlQuery;

    public void addProductToDB(ProductDto productDto) {
        try
        {
            jdbcTemplate.update(sqlQuery.addProductQuery,
                    productDto.getProductNo(),
                    productDto.getCategoryId(),
                    productDto.getVendorId(),
                    productDto.getBrandId(),
                    productDto.getAltNo(),
                    productDto.getDescription(),
                    productDto.getCostPrice(),
                    productDto.getMarkup(),
                    productDto.getRetailPrice(),
                    productDto.getQuantity(),
                    productDto.getMinProductQuantity(),
                    productDto.getReturnRule(),
                    productDto.getImage(),
                    productDto.getCreatedDate(),
                    productDto.getImeiNo(),
                    productDto.getTax());
            System.out.println("Product Added Successfully");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void editProductToDB(ProductDto productDto) {

        try
        {
            jdbcTemplate.update(sqlQuery.editProductQuery,
                    productDto.getProductNo(),
                    productDto.getCategoryId(),
                    productDto.getVendorId(),
                    productDto.getBrandId(),
                    productDto.getAltNo(),
                    productDto.getDescription(),
                    productDto.getCostPrice(),
                    productDto.getMarkup(),
                    productDto.getRetailPrice(),
                    productDto.getQuantity(),
                    productDto.getMinProductQuantity(),
                    productDto.getReturnRule(),
                    productDto.getImage(),
                    productDto.getImeiNo(),
                    productDto.getTax(),
                    productDto.getProductId());

            System.out.println("Product Edited Successfully");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
        public List<ProductDto> getProductDetails() {

            List<ProductDto> productList = new ArrayList<>();

            try
            {
                productList = jdbcTemplate.query(sqlQuery.getProductDetails,new ProductMapper());

                System.out.println("Send Product Details Successfully");
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
            return productList;
        }

    private final class ProductMapper implements RowMapper<ProductDto>
        {

            @Override
            public ProductDto mapRow(ResultSet rs, int rowNum) throws SQLException {

                ProductDto product = new ProductDto();

                product.setProductId(rs.getInt("PRODUCT_ID"));
                product.setProductNo(rs.getString("PRODUCT_NO"));
                product.setCategoryId(rs.getInt("CATEGORY_ID"));
                product.setCategoryName(jdbcTemplate.queryForObject(sqlQuery.getCategoryName, new Object[] {product.getCategoryId()},String.class));
                product.setVendorId(rs.getInt("VENDOR_ID"));
                product.setVendorName(jdbcTemplate.queryForObject(sqlQuery.getVendorName, new Object[] {product.getVendorId()},String.class));
                product.setAltNo(rs.getString("ATL_NO"));
                product.setDescription(rs.getString("DESCRIPTION"));
                product.setCostPrice(rs.getString("COST_PRICE"));
                product.setMarkup(rs.getString("MARKUP"));
                product.setRetailPrice(rs.getString("RETAIL_PRICE"));
                product.setStock(rs.getString("QUANTITY"));
                product.setQuantity("1");
                product.setMinProductQuantity(rs.getString("MIN_PRODUCT"));
                product.setReturnRule(rs.getString("RETURN_RULE"));
                product.setImage(rs.getString("IMAGE"));
                product.setCreatedDate(rs.getString("CREATED_DATE"));
                product.setBrandId(rs.getInt("BRAND_ID"));
                product.setBrandName(jdbcTemplate.queryForObject(sqlQuery.getBrandName, new Object[] {product.getBrandId()},String.class));
                product.setImeiNo(rs.getString("IMEI_NUMBER"));
                product.setTax(rs.getString("TAX"));
                //product.setQuantityForSell(1);


                return product;
            }
        }

    public List<TransactionLineItemDto> getProductHistoryFromDB(int productId) {

        List<TransactionLineItemDto> productHistory = new ArrayList<>();

        try
        {
            productHistory = jdbcTemplate.query(SQLQueries.getProductHistory,new ProductHistoryMapper(), productId);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return productHistory;

    }

    private final class ProductHistoryMapper implements RowMapper<TransactionLineItemDto>
    {

        @Override
        public TransactionLineItemDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            TransactionLineItemDto productHistory = new TransactionLineItemDto();

            productHistory.setTransactionDate(rs.getString("DATE"));
            productHistory.setProductId(rs.getInt("PRODUCT_ID"));
            productHistory.setProductNumber(rs.getString("PRODUCT_NO"));
            productHistory.setProductDescription(rs.getString("DESCRIPTION"));
            productHistory.setQuantity(rs.getInt("QUANTITY"));
            productHistory.setRetail(rs.getDouble("RETAIL"));
            productHistory.setCost(rs.getDouble("COST"));
            productHistory.setDiscount(rs.getDouble("DISCOUNT"));

            //TODO NEED TO FIND THE LOGIC TO TAKE THIS DB CALL OUT FROM SUM OF QUANTITY
            productHistory.setProductCount(jdbcTemplate.queryForObject(SQLQueries.getProductHistoryCount,new Object[] {productHistory.getProductId()}, String.class));

            return productHistory;
        }
    }
    public void deleteProductToDB(String productNo) {

    }


    }

