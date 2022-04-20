package _05_netflix;

public class NetflixRunner {

	public static void main(String[] args) {
		
		Movie noWayHome =new Movie("noWayHome",5);
		Movie revengeofthesith=new Movie("revengeofthesith",4);
		Movie dune =new Movie("dune",3);
		Movie harrypotter=new Movie("harrypotter",2);
		Movie Batman =new Movie("Batman",1);
		noWayHome.getTicketPrice();
		NetflixQueue  netflix=new NetflixQueue();
		netflix.addMovie(noWayHome);
		netflix.addMovie(revengeofthesith);
		netflix.addMovie(dune);
		netflix.addMovie(harrypotter);
		netflix.addMovie(Batman);
		netflix.printMovies();
	   System.out.println("the best movie is..."+netflix.getBestMovie());
	   System.out.println("the second best movie is.." +netflix.getMovie(1));
		
	}
}
