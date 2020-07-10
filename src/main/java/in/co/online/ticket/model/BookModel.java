package in.co.online.ticket.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.online.ticket.bean.BookBean;
import in.co.online.ticket.bean.MovieBean;
import in.co.online.ticket.bean.UserBean;
import in.co.online.ticket.exception.ApplicationException;
import in.co.online.ticket.exception.DatabaseException;
import in.co.online.ticket.exception.DuplicateRecordException;
import in.co.online.ticket.util.JDBCDataSource;

public class BookModel {
	
	private static Logger log = Logger.getLogger(BookModel.class);

	/**
	 * Find next PK of Role
	 * 
	 * @throws DatabaseException
	 */
	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM T_Book");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}
	
	public BookBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM T_Book WHERE ID=?");
		BookBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new BookBean();
				bean.setId(rs.getLong(1));
                bean.setUserName(rs.getString(2));
 				bean.setMovieId(rs.getLong(3));
 				bean.setMovieName(rs.getString(4));
 				bean.setBookDate(rs.getDate(5));
 				bean.setShowTime(rs.getString(6));
 				bean.setNoOfPerson(rs.getLong(7));
 				bean.setFinalAmount(rs.getLong(8));
 				bean.setCreatedBy(rs.getString(9));
 				bean.setModifiedBy(rs.getString(10));
 				bean.setCreatedDatetime(rs.getTimestamp(11));
 				bean.setModifiedDatetime(rs.getTimestamp(12));
 				bean.setMobileNo(rs.getString(13));
 				bean.setEmalid(rs.getString(14));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}
	
	public long add(BookBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;
		
		UserModel uModel=new UserModel();
		UserBean uBean=uModel.findByPK(bean.getUserId());
		
		MovieModel mModel=new MovieModel();
		MovieBean mBean=mModel.findByPK(bean.getMovieId());
		
		
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO T_Book VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2,uBean.getFirstName()+" "+uBean.getLastName());
			pstmt.setLong(3, bean.getMovieId());
			pstmt.setString(4,mBean.getName());
			pstmt.setDate(5,new java.sql.Date(bean.getBookDate().getTime()) );
			pstmt.setString(6, bean.getShowTime());
			pstmt.setLong(7, bean.getNoOfPerson());
			pstmt.setLong(8, bean.getFinalAmount());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());
			pstmt.setString(13,uBean.getMobileNo());
			pstmt.setString(14,bean.getEmalid());
			
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}
	
	public void delete(BookBean bean) throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM T_Book WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			// log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}
	
	public void update(BookBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;
		
		UserModel uModel=new UserModel();
		UserBean uBean=uModel.findByPK(bean.getUserId());
		
		MovieModel mModel=new MovieModel();
		MovieBean mBean=mModel.findByPK(bean.getMovieId());
		
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE T_Book SET UserName=?,MovieId=?,MovieName=?,bookDate=?,ShowTime=?,NoOfPerson=?,FinalPrice=?,CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=?,mobileNo=?,email=? WHERE ID=?");
			pstmt.setString(1,uBean.getFirstName()+" "+uBean.getLastName());
			pstmt.setLong(2, bean.getMovieId());
			pstmt.setString(3,mBean.getName());
			pstmt.setDate(4,new java.sql.Date(bean.getBookDate().getTime()) );
			pstmt.setString(5, bean.getShowTime());
			pstmt.setLong(6, bean.getNoOfPerson());
			pstmt.setLong(7, bean.getFinalAmount());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());
			pstmt.setString(12,uBean.getMobileNo());
			pstmt.setString(13,bean.getEmalid());
			pstmt.setLong(14, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Role ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}
	
	public List search(BookBean bean) throws ApplicationException {
        return search(bean, 0, 0);
    }

    /**
     * Search Role with pagination
     * 
     * @return list : List of Roles
     * @param bean
     *            : Search Parameters
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * 
     * @throws DatabaseException
     *  @throws ApplicationException
     */
    public List search(BookBean bean, int pageNo, int pageSize)
            throws ApplicationException {
        log.debug("Model search Started");
        StringBuffer sql = new StringBuffer("SELECT * FROM T_Book WHERE 1=1");
        if (bean != null) {
            if (bean.getId() > 0) {
                sql.append(" AND id = " + bean.getId());
            }
           
            if (bean.getUserName() != null && bean.getUserName().length() > 0) {
				sql.append(" AND UserNAME LIKE '" + bean.getUserName() + "%'");
            }
            if (bean.getMovieName() != null && bean.getMovieName().length() > 0) {
				sql.append(" AND MovieNAME LIKE '" + bean.getMovieName() + "%'");
            }
            
        }

        // if page size is greater than zero then apply pagination
        if (pageSize > 0) {
            // Calculate start record index
            pageNo = (pageNo - 1) * pageSize;
            sql.append(" Limit " + pageNo + ", " + pageSize);
            // sql.append(" limit " + pageNo + "," + pageSize);
        }
        ArrayList list = new ArrayList();
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bean = new BookBean();
                bean.setId(rs.getLong(1));
                bean.setUserName(rs.getString(2));
 				bean.setMovieId(rs.getLong(3));
 				bean.setMovieName(rs.getString(4));
 				bean.setBookDate(rs.getDate(5));
 				bean.setShowTime(rs.getString(6));
 				bean.setNoOfPerson(rs.getLong(7));
 				bean.setFinalAmount(rs.getLong(8));
 				bean.setCreatedBy(rs.getString(9));
 				bean.setModifiedBy(rs.getString(10));
 				bean.setCreatedDatetime(rs.getTimestamp(11));
 				bean.setModifiedDatetime(rs.getTimestamp(12));
 				bean.setMobileNo(rs.getString(13));
 				bean.setEmalid(rs.getString(14));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
        	e.printStackTrace();
           log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in search Role");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model search End");
        return list;
    }
    
    public List list() throws ApplicationException {
        return list(0, 0);
    }

    /**
     * Get List of Role with pagination
     * 
     * @return list : List of Role
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws DatabaseException
     *  @throws ApplicationException
     */
    public List list(int pageNo, int pageSize) throws ApplicationException {
        log.debug("Model list Started");
        ArrayList list = new ArrayList();
        StringBuffer sql = new StringBuffer("select * from T_Book");
        // if page size is greater than zero then apply pagination
        if (pageSize > 0) {
            // Calculate start record index
            pageNo = (pageNo - 1) * pageSize;
            sql.append(" limit " + pageNo + "," + pageSize);
        }
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	BookBean bean = new BookBean();
            	bean.setId(rs.getLong(1));
                bean.setUserName(rs.getString(2));
 				bean.setMovieId(rs.getLong(3));
 				bean.setMovieName(rs.getString(4));
 				bean.setBookDate(rs.getDate(5));
 				bean.setShowTime(rs.getString(6));
 				bean.setNoOfPerson(rs.getLong(7));
 				bean.setFinalAmount(rs.getLong(8));
 				bean.setCreatedBy(rs.getString(9));
 				bean.setModifiedBy(rs.getString(10));
 				bean.setCreatedDatetime(rs.getTimestamp(11));
 				bean.setModifiedDatetime(rs.getTimestamp(12));
 				bean.setMobileNo(rs.getString(13));
 				bean.setEmalid(rs.getString(14));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
          //  log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting list of Role");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model list End");
        return list;

    }

}
