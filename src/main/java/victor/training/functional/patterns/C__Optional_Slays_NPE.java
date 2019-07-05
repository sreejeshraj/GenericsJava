package victor.training.functional.patterns;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.Optional.ofNullable;

import java.util.Optional;

import lombok.Data;

/* "I call it my billion-dollar mistake. 
 * It was the invention of the null reference in 1965..."
 *  -- Sir Charles Antony Richard  */

// Get a discount line to print in UI

class DiscountService {
	public String getDiscountLine(Customer customer) {
		return customer.getMemberCard()
				.flatMap(card -> getApplicableDiscountPercentage(card))
				.map(d -> "Discount%: " + d)
				.orElse("");
	}
		
	private Optional<Object> getApplicableDiscountPercentage(MemberCard card) { 
		if (card.getFidelityPoints() >= 100) {
			return of(5);
		}
		if (card.getFidelityPoints() >= 50) {
			return of(3);
		}
		return empty();
	}
		
	// test: 60, 10, no MemberCard
	public static void main(String[] args) {
		DiscountService discountService = new DiscountService();
		System.out.println(discountService.getDiscountLine(new Customer(new MemberCard(60))));
		System.out.println(discountService.getDiscountLine(new Customer(new MemberCard(10))));
		System.out.println(discountService.getDiscountLine(new Customer()));
	}
}








// VVVVVVVVV ==== supporting (dummy) code ==== VVVVVVVVV
class Customer {
	private MemberCard memberCard;
	public Customer() {
	}
	public Customer(MemberCard profile) {
		this.memberCard = profile;
	}
	public Optional<MemberCard> getMemberCard() {
		return ofNullable(memberCard);
	}
}

@Data
class MemberCard {
	private final int fidelityPoints;
}
