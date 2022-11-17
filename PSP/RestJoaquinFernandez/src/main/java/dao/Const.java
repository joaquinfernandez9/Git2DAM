package dao;

public class Const {
    public static final String SELECT_FROM_ARTICLE = "select * from article";
    public static final String getArticlesOfReader = "select article.* from article, subscribe, reader, newspaper where reader.id = subscribe.id_reader and subscribe.id_newspaper = newspaper.id and newspaper.id = article.id_newspaper and reader.id = ? and article.id not in (select id_article from readarticle where id_reader = ?) order by article.id_newspaper, article.id";
    public static final String getAllArticlesByType = "select * from article where id_type=?";
    public static final String getArticle = "select * from article where id=?";
    public static final String deleteArticle = "delete from article where id=?";
    public static final String updateArticle = "update article set name_article=? where id=?";
    public static final String getAllNewspaper = "select * from newspaper";
    public static final String ARTICLE = "article";
    public static final String NAME_ARTICLE = "name_article";
    public static final String ID = "id";
    public static final String ID_TYPE = "id_type";
    public static final String ID_NEWSPAPER = "id_newspaper";
    public static final String INTEGRITY_CONSTRAINT_VIOLATION = "IntegrityConstraintViolation";
    public static final String ID_READER = "id_reader";
    public static final String logQuery = "Select id_reader from login where user=? and password =?";
    public static final String getLoginID = "select id_reader from login where user=? and password =?";
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    public static final String getNewspaper = "select * from newspaper where id=?";
    public static final String NEWSPAPER = "newspaper";
    public static final String NAME_NEWSPAPER = "name_newspaper";
    public static final String RELEASE_DATE = "release_date";
    public static final String DeleteFromArticle = "delete from article where id_newspaper=?";
    public static final String deleteFromReadArticle = "delete from readarticle where id_article in (select id from article where id_newspaper = ?)";
    public static final String deleteFromSubscribe = "delete from subscribe where id_newspaper=?";
    public static final String DELETE_FROM_NEWSPAPER_WHERE_ID = "delete from newspaper where id=?";
    public static final String ERROR_IN_ROLLBACK = "Error in rollback";
    public static final String updateNewspaper = "update newspaper set name_newspaper=?, release_date=? where id=?";
    public static final String firstQuery = "select type.description, count(readarticle.id_reader), article.name_article from type, article, readarticle where type.id = article.id_type and article.id = readarticle.id_article group by type.description, article.name_article;";
    public static final String secondQuery = "select name_reader from reader, subscribe, newspaper where newspaper.id = subscribe.id_newspaper and subscribe.id_reader = reader.id and newspaper.id = ? and subscribe.cancellation_date is null and subscribe.sing_date in (select min(subscribe.sing_date) from subscribe) and id_reader > 0 LIMIT 5";
    public static final String thirdQuery = "select article.name_article, newspaper.name_newspaper from article join newspaper on article.id_newspaper = newspaper.id, type where type.id = article.id_type and article.id_newspaper = newspaper.id and type.description = ?";
    public static final String fourthQuery = "SELECT a.name_article, a.id, (SELECT COUNT(*) FROM readarticle WHERE id_reader = r.id_reader AND r.ranking < 5) AS bad_ratings FROM article a LEFT JOIN readarticle r ON a.id = r.id_article WHERE a.id_newspaper = ? AND r.ranking < 5";
    public static final String ARTICLE_NAME = "name_article";
    public static final String DESCRIPTION = "description";
    public static final String COUNT_READARTICLE_ID_READER = "count(readarticle.id_reader)";
    public static final String NAME_READER = "name_reader";
    public static final String BIRTH_READER = "birth_reader";
    public static final String ARTICLE_NAME_ARTICLE = "article.name_article";
    public static final String NEWSPAPER_NAME_NEWSPAPER = "newspaper.name_newspaper";
    public static final String SELECT_FROM_READARTICLE = "select * from readarticle";
    public static final String getAllReadArticles = "select * from readarticle where id_reader=?";
    public static final String addReadArticle = "INSERT INTO readarticle (id_article, id_reader, ranking) VALUES (?,?,?)";
    public static final String deleteReadArticle = "delete from readarticle where id_reader =?";
    public static final String ID_ARTICLE = "id_article";
    public static final String RANKING = "ranking";
    public static final String DELETE_FROM_LOGIN_WHERE_ID_READER = "DELETE FROM login WHERE id_reader = ?";
    public static final String getAllReaders = "select * from reader inner join login l on reader.id = l.id_reader where id> 0";
    public static final String getAllReadersSubToNP = "select * from reader inner join login on reader.id = login.id_reader inner join subscribe on reader.id = subscribe.id_reader inner join newspaper on newspaper.id = subscribe.id_newspaper where reader.id = subscribe.id_reader and newspaper.id = subscribe.id_newspaper and newspaper.id = ?";
    public static final String getAllReadersOfAType = "select * from reader inner join login on reader.id = login.id_reader inner join subscribe on reader.id = subscribe.id_reader inner join newspaper on newspaper.id = subscribe.id_newspaper inner join article on newspaper.id = article.id_newspaper inner join type on article.id_type = type.id where subscribe.cancellation_date IS NULL and description = ?";
    public static final String getReader = "select * from reader inner join login on reader.id = login.id_reader where reader.id = ?";
    public static final String DELETE_FROM_READER_WHERE_ID = "delete from reader where id=?";
    public static final String DELETE_FROM_READARTICLE_WHERE_ID_READER = "delete from readarticle where id_reader =?";
    public static final String DELETE_FROM_SUBSCRIBE_WHERE_ID_READER = "delete from subscribe where id_reader =?";
    public static final String updateReader = "UPDATE reader set name_reader =?, birth_reader=? where id = ?";
    public static final String updateLogin = "UPDATE  login set username = ?, password=? where id_reader= ?";
    public static final String addReader = "INSERT INTO reader (name_reader, birth_reader) VALUES (?,?)";
    public static final String addLogin = "INSERT INTO login (user, password, id_reader) VALUES (?,?, ?)";
    public static final String addSubscribe = "insert into subscribe (id_newspaper, id_reader, sing_date, cancellation_date) values (?,?,?,?)";
    public static final String getAllFromSubscribe = "select * from subscribe";
    public static final String getAllSubscriptionsOfReader = "select * from subscribe where id_reader=?";
    public static final String SING_DATE = "sing_date";
    public static final String CANCELLATION_DATE = "cancellation_date";
    public static final String getAllTypes = "select * from type";
    public static final String getType = "select * from type where id=?";
    public static final String TYPE = "type";
    public static final String DELETE_FROM_TYPE_WHERE_ID = "delete from type where id=?";
    public static final String updateType = "update type set description=? where id=?";
    public static final String DELETE_FROM_SUBSCRIBE_WHERE_ID_READER_AND_ID_NEWSPAPER = "DELETE FROM subscribe WHERE id_reader = ? AND id_newspaper = ?";
    public static final String DELETE_FROM_READ_ARTICLES_WHERE_ID_READER = "DELETE FROM read_articles WHERE id_reader = ?";

    public Const() {
    }


}
